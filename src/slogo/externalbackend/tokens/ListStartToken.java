package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class ListStartToken extends Token{

    public ListStartToken(){
        tokenType = TokenType.ListStartToken;
        tokenName = "ListStart";
    }

}
