/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 *
 * @author Marcelo
 */
public class ApiRequestException extends Exception{
    
    private int httpResponseCode;

    public ApiRequestException(int httpResponseCode, String message) {
        super(message);
        this.httpResponseCode = httpResponseCode;
    }

    public int getHttpResponseCode() {
        return httpResponseCode;
    }

    public void setHttpResponseCode(int httpResponseCode) {
        this.httpResponseCode = httpResponseCode;
    }
    
    
}
