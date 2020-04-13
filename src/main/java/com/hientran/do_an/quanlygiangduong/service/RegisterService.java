package com.hientran.do_an.quanlygiangduong.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class RegisterService {
    private final Logger log = LoggerFactory.getLogger(RegisterService.class);

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789qwertyuiopasdfghjklzxcvbnm";

    private final String RABBIT_EXCHANGE_REGISTER = "UM_Exchange";

    private RegisterRepository registerRepository;

    private RegisterMapper registerMapper;

    private RegisterFileMapper registerFileMapper;

    private final RabbitMQClient rabbitMQClient;

    private EmailSend emailSend;


    public RegisterService(RabbitMQClient rabbitMQClient,
                           RegisterRepository registerRepository,
                           RegisterMapper registerMapper,
                           EmailSend emailSend,
                           RegisterFileMapper registerFileMapper,
                           RegisterFileRepository registerFileRepository,
                           OrgInfoMapper orgInfoMapper,
                           UserRegisterRepository userRegisterRepository) {
        this.rabbitMQClient = rabbitMQClient;
        this.registerRepository = registerRepository;
        this.registerMapper = registerMapper;
        this.emailSend = emailSend;
        this.registerFileMapper = registerFileMapper;

    }

    /**
     * random id for registercode
     */
    public String randomAlphaNumeric(int count) {
        while (true) {
            StringBuilder builder = new StringBuilder();
            while (count-- != 0) {
                int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            }
            Optional<Register> existing = registerRepository.findOneByRegisterCode(builder.toString());
            if (existing.isPresent() == false) {
                return builder.toString();
            }
        }

    }

    /**
     * get register by taxcode or registercode
     */
    public Optional<Register> findByTaxCodeOrRegCode(String request) throws ServiceException, Exception {

        if (14 == request.length()) {

            return registerRepository.findByTaxCode(request).filter(Objects::nonNull)
                .map(registers -> registers.stream().sorted(new Comparator<Register>() {
                    @Override
                    public int compare(Register o1, Register o2) {
                        return o2.getupdatedDate().compareTo(o1.getupdatedDate());
                    }
                }).findFirst().get());
        }
        if (16 == request.length()){
            Optional<Register> register = registerRepository.findOneByRegisterCode(request);
            return registerRepository.findByTaxCode(register.get().getTaxCode()).filter(Objects::nonNull)
                .map(registers -> registers.stream().sorted(new Comparator<Register>() {
                    @Override
                    public int compare(Register o1, Register o2) {
                        return o2.getupdatedDate().compareTo(o1.getupdatedDate());
                    }
                }).findFirst().get());
        }

        return null;
    }

    /**
     * Create new register
     */
    public RegisterResponse register(RegisterRequest request) throws Exception, ServiceException {

        try {
            if (request == null || request.getRegister() == null)
                ServiceUtil.generateEmptyPayloadError();
            log.info("request: {}", request.getRegister().getTaxCode());
            Optional<List<Register>> registers = registerRepository.findByTaxCode(request.getRegister().getTaxCode())
                .filter(registers1 -> registers1 != null).map(registers1 ->
                    registers1.stream().filter(c -> c.getStatus() == 1 || c.getStatus() == 2 || c.getStatus() == 6).collect(Collectors.toList())
                ).filter(registers1 -> registers1 != null);

            if (registers.isPresent())
                throw ServiceExceptionBuilder.newBuilder().addError(
                    new ValidationErrorResponse("register", ValidationError.Duplicate)
                ).build();


            //    send message to rabbitMQ
            rabbitMQClient.sendMessage(RABBIT_EXCHANGE_REGISTER, emailSend);
            Register newRegister = registerMapper.registerDTOToRegister(request.getRegister());
            newRegister.setStatus(1);
            newRegister.setRegisterCode(randomAlphaNumeric(9));
            newRegister.setCreatedDate(Instant.now());
            newRegister.setUpdatedDate(Instant.now());
            newRegister.setCreatedBy("GUEST");
            newRegister.setUpdatedBy("GUEST");

//            newRegister.setCreatedBy(SecurityUtils.getCurrentUserLogin().get());
//            newRegister.setUpdatedBy(SecurityUtils.getCurrentUserLogin().get());
            Optional<RegisterDTO> updatedRegister = Optional.of(
                Optional.of(registerRepository.save(newRegister)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(register -> {
                    log.debug("Inserted Information for Register: {}", register);
                    return register;
                })
                .map(RegisterDTO::new);
            RegisterResponse response = new RegisterResponse();
            if (updatedRegister.isPresent())
                response.setRegister(updatedRegister.get());
            return response;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get infor organ from DKDN
     */
    public BusinessQueryResponse getBusinessInfo(BusinessQueryRequest orgCode) throws ServiceException {
        try {
            BusinessQueryResponse response = new BusinessQueryResponse();
            if (orgCode == null)
                ServiceUtil.generateEmptyPayloadError();
//
//          {xu ly call API sang DKDN de lay data cho BusinessRegistrationClient}
//
            if (null == response.getBusinessRegistrationClient()) {
                //chua co data
//                throw ServiceExceptionBuilder.newBuilder()
//                    .addError(new ValidationErrorResponse("organ", ValidationError.NotNull))
//                    .build();
                //tra ve null
                return response;

            }
            return response;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Update register file
     */
    public RegisterDocResponse updateFile(RegisterDocRequest request) throws ServiceException, Exception {

        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            if(request.getRegCode().length() != 9)
                throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("registerCode", ValidationError.Size))
                    .build();
            Optional<Register> existing = registerRepository.findOneByRegisterCode(request.getRegCode());
            if (existing.isPresent() == false) {
                throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("registerCode", ValidationError.NotNull))
                    .build();
            }
            request.getDocs().stream().forEach(doc -> {
                RegisterFile registerFile = registerFileMapper.registerFileDTOToRegisterFile(doc);
                registerFile.setCreatedDate(Instant.now());
                registerFile.setUpdatedDate(Instant.now());
                registerFile.setRegister(existing.get());
                registerFileRepository.save(registerFile);

            });
            existing.get().setStatus(2);
            Optional<RegisterDTO> updatedRegister = Optional.of(
                Optional.of(registerRepository.save(existing.get())))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(register -> {
                    log.debug("Inserted Information for Register: {}", register);
                    return register;
                })
                .map(RegisterDTO::new);
            RegisterDocResponse response = new RegisterDocResponse();
            if (!updatedRegister.isPresent()) {
                throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("register", ValidationError.AssertFalse))
                    .build();
            }
            response.setSuccess(true);
            response.setErrorCode(ValidationError.AssertTrue);
            return response;

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }


    public GetRegisterByTaxCodeResponse getRegisterByTaxCode(GetRegisterByTaxCodeRequest request) throws ServiceException {
        try {
            GetRegisterByTaxCodeResponse response = new GetRegisterByTaxCodeResponse();
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            Optional<List<RegisterDTO>> registerDTOs = registerRepository.findOneByTaxCodeContaining(request.getTaxCode())
                .filter(Objects::nonNull).map(registers ->
                    registers.stream().map(RegisterDTO::new).collect(Collectors.toList()));

            if (registerDTOs.isPresent())
                response.setRegister(registerDTOs.get());
            return response;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public RegisterListResponse getRegisterWaitingList(RegisterListRequest request) throws Exception, ServiceException {
        return null;
    }

    /**
     * Re-Register infor for registers
     */
    public UpdateRegisterResponse updateRegister(UpdateRegisterRequest request) throws ServiceException, Exception {

        try {
            if (null == request)
                ServiceUtil.generateEmptyPayloadError();

            Optional<Register> existing = findByTaxCodeOrRegCode(request.getRegCodeOrTaxCode());/*registerRepository.findOneByRegisterCode(request.getRegCodeOrTaxCode());*/
            if (existing.isPresent() == false) {
                throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("register", "validation.constraints.NotExisted"))
                    .build();
            }
            if (existing.get().getStatus() <= 2) {
                //user can update all info
                request.getRegisterDTO().setId(existing.get().getId());
                request.getRegisterDTO().setRegisterCode(existing.get().getRegisterCode());
                request.getRegisterDTO().setStatus(existing.get().getStatus());
                Register registerUpdate = registerMapper.registerDTOToRegister(request.getRegisterDTO());
                registerUpdate.setCreatedDate(existing.get().getCreatedDate());
                registerUpdate.setUpdatedDate(Instant.now());
                registerUpdate.setCreatedBy("GUEST");
                registerUpdate.setUpdatedBy("GUEST");
                registerRepository.save(registerUpdate);

                UpdateRegisterResponse response = new UpdateRegisterResponse();
                response.setSuccess(true);
                response.setErrorCode("1");
                return response;
            } else {
                //user just can add role and adjust some info
                Register registerUpdate = registerMapper.registerDTOToRegister(request.getRegisterDTO());

                registerUpdate.setStatus(1);
                registerUpdate.setTaxCode(existing.get().getTaxCode());
                registerUpdate.setOrgCode(existing.get().getOrgCode());
                registerUpdate.setOrgName(existing.get().getOrgName());
                registerUpdate.setInChargeName(existing.get().getInChargeName());
                registerUpdate.setPreRegisType(existing.get().getPreRegisType());
                registerUpdate.setRegisterCode(randomAlphaNumeric(9));
                registerUpdate.setRepName(existing.get().getRepName());
                registerUpdate.setRepIdType(existing.get().getRepIdType());
                registerUpdate.setRepIdNo(existing.get().getRepIdNo());
                registerUpdate.setCreatedDate(Instant.now());
                registerUpdate.setUpdatedDate(Instant.now());
                registerUpdate.setCreatedBy("GUEST");
                registerUpdate.setUpdatedBy("GUEST");
                log.info("register code: {}", registerUpdate.getRegisterCode());
                registerRepository.save(registerUpdate);

                UpdateRegisterResponse response = new UpdateRegisterResponse();
                response.setSuccess(true);
                response.setErrorCode(ValidationError.AssertTrue);
                return response;
            }

        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public GetOrgManagerResponse orgManager(GetOrgManagerRequest request) throws ServiceException, Exception {
        try {
            if (null == request)
                ServiceUtil.generateEmptyPayloadError();
            Optional<Register> register = registerRepository.findOneByChapterCode(request.getChapterCode());
            if (register.isPresent() == false)
                throw ServiceExceptionBuilder.newBuilder()
                    .addError(new ValidationErrorResponse("register", "validation.constraints.NotExisted"))
                    .build();
            GetOrgManagerResponse response = new GetOrgManagerResponse();
            response.setTaxCode(register.get().getTaxCode());
            response.setOrgCode(register.get().getOrgCode());
            response.setOrgName(register.get().getOrgName());
            return response;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }


    public GetRegisterByRegisCodeResponse getRegisterByRegisCode(GetRegisterByRegisCodeRequest request) throws ServiceException {
        try {
            GetRegisterByRegisCodeResponse response = new GetRegisterByRegisCodeResponse();
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();
            Optional<RegisterDTO> registerDTO = registerRepository.findOneByRegisterCode(request.getRegisCode())
                .filter(Objects::nonNull).map(RegisterDTO::new);

            if (registerDTO.isPresent())
                response.setRegister(registerDTO.get());
            return response;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
    public RegisterListResponse getRegisterApproveList(RegisterListRequest request) throws Exception, ServiceException {
        return null;
    }

    public RegisterListResponse getRegisterList(RegisterListRequest request) throws Exception, ServiceException {

        try {
            if (request == null)
                ServiceUtil.generateEmptyPayloadError();

            if (request.getPageNumber() < 0)
                request.setPageNumber(0);

            if (request.getPageSize() < 1)
                request.setPageSize(Constants.DEFAULT_PAGE_SIZE);

            if (! RegisterFileStatus.isValidStatus(request.getStatus())) {
                request.setStatus(RegisterFileStatus.WAITING.getStatusName());
            }

            List<Integer> statusList = RegisterFileStatus.getValuesByName(request.getStatus());

            Page<RegisterDTO> registers = registerRepository.findByStatusIn(
                PageRequest.of(request.getPageNumber(), request.getPageSize()), statusList).map(RegisterDTO::new);

            RegisterListResponse getRegisterListResponse = new RegisterListResponse();
            getRegisterListResponse.setPage(OptimizedPage.convert(registers));

            return getRegisterListResponse;
        } catch (ServiceException e){
            throw e;
        } catch (Exception e){
            throw e;
        }
    }

    public findOrgResponse getOrgInfo(findOrgRequest request) throws ServiceException {
        try{
            findOrgResponse response=new findOrgResponse();
            if(request ==null)
                ServiceUtil.generateEmptyPayloadError();
            Optional<List<Register>> registers=Optional.ofNullable(null);
            if(request.getFindType().equalsIgnoreCase("tax_code"))
                registers=registerRepository.findByTaxCodeContaining(request.getFindValue()).filter(Objects::nonNull)
                    .map(registers1 -> {
                       // log.info("registerDTO: {}",registers1.toString());
                        if (request.getFindValue().equalsIgnoreCase(""))
                            return registers1.stream().filter(Objects::nonNull).filter(register -> register.getTaxCode().length() >= 10)
                                .collect(Collectors.toList());
                        return registers1;
                    });
            if(request.getFindType().equalsIgnoreCase("org_name"))
                registers=registerRepository.findOneByOrgNameContaining(request.getFindValue()).filter(Objects::nonNull)
                    .map(registers1 ->{
                      //  log.info("registerDTO: {}",registers1.toString());
                        if(request.getFindValue().equalsIgnoreCase(""))
                            return registers1.stream().filter(Objects::nonNull).filter(register -> register.getOrgName().length()>=20).collect(Collectors.toList());
                        return registers1;
                    });
            if(registers.isPresent()){
                Optional<List<OrgInfoChilDTO>> orgInfoDTOs=registers.map(orgInfoMapper::orgInfosToOrgInfoDTOs);
                if(orgInfoDTOs.isPresent())
                    response.setOrgInfo(orgInfoDTOs.get().stream().filter(Objects::nonNull).collect(Collectors.toList()));
            }
            return response;
        } catch (ServiceException e) {
            throw e;
        }catch (Exception e){
            throw e;
        }
    }

}
