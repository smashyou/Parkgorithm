/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parkgo.parkgorithm.background.exception;

/**
 *
 * @author Antony
 */
public final class AccountException extends Exception{
    public static final int ARGUMENT_PASSED_IS_NULL            = 0x0001; 
    public static final int ARGUMENT_PASSED_IS_OF_INVALID_TYPE = 0x0002;
    public static final int ARGUMENT_PASSED_HAS_INVALID_ID     = 0x0BAD; // If ID is Invalid then the this is bad
    public static final int UNKNOWN_ERROR_CODE                 = 0xBBAD; 
    public static final int ACCOUNT_ALREADY_LOGGED_ON          = 0xABCD;
    public static final int ARGUMENT_PASSED_IS_INVALID         = 0xFEED;
    private final int m_iErrorCode;
    
    public AccountException(String _strMsg, int _iErrorCode){
        super(_strMsg);
        switch (_iErrorCode){
            case ARGUMENT_PASSED_IS_NULL:
            case ARGUMENT_PASSED_IS_OF_INVALID_TYPE:
            case ARGUMENT_PASSED_HAS_INVALID_ID:
            case ACCOUNT_ALREADY_LOGGED_ON:
            case ARGUMENT_PASSED_IS_INVALID:
                m_iErrorCode = _iErrorCode;
                break;
            default:
                m_iErrorCode = UNKNOWN_ERROR_CODE;
        }
    }
    
    public int getErrorCode(){
        return (m_iErrorCode);
    }
}
