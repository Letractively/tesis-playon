package tesis.playon.restful.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class ResponseJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private String code;

    private String message;

    public boolean isSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}