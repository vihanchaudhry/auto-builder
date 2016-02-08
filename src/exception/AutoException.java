package exception;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AutoException extends Exception {
    // Constants
    public static final int ERROR_OPTION_DATA_MISSING = 1;
    public static final int ERROR_INCORRECT_FILENAME = 101;
    public static final int ERROR_AUTOMOBILE_PRICE_MISSING = 102;
    public static final int ERROR_SERIALIZATION_FAILED = 103;
    public static final int ERROR_DESERIALING_AUTO = 104;
    //public final int ERROR = 0;

    private int errorNum;
    private String errorMsg;

    public AutoException() {
        super();
        printMyProblem();
        logMyProblem();
        fix();
    }

    public AutoException(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
        printMyProblem();
        logMyProblem();
        fix();
    }

    public AutoException(int errorNum) {
        super();
        this.errorNum = errorNum;
        printMyProblem();
        logMyProblem();
        fix();
    }

    public AutoException(int errorNum, String errorMsg) {
        super();
        this.errorNum = errorNum;
        this.errorMsg = errorMsg;
        printMyProblem();
        logMyProblem();
        fix();
    }

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorno) {
        this.errorNum = errorno;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void printMyProblem() {
        System.out.println("AutoException [errorNum=" + errorNum + ", errorMsg=" + errorMsg + "]");
    }

    public void logMyProblem() {
        Logger logger = Logger.getLogger(this.getClass().getName());
        FileHandler fileHandler;

        try {
            fileHandler = new FileHandler(this.getClass().getName() + ".log");
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.setUseParentHandlers(false);
            logger.info("AutoException [errorNum=" + errorNum + ", errorMsg=" + errorMsg + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fix() {
        FixModel fixModel = new FixModel();
        FixUtil fixUtil = new FixUtil();

        switch (errorNum) {
            case ERROR_OPTION_DATA_MISSING:
                fixModel.fixOptionDataMissing();
                break;
            case ERROR_INCORRECT_FILENAME:
                fixUtil.fixIncorrectFilename();
                break;
            case ERROR_AUTOMOBILE_PRICE_MISSING:
                break;
            case ERROR_SERIALIZATION_FAILED:
                fixUtil.fixSerializationFailed();
                break;
            case ERROR_DESERIALING_AUTO:
                fixUtil.fixDeserializingAuto();
            default:
                break;
        }
    }
}
