package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class GroupStartToken extends Token{

    public GroupStartToken(){
        tokenType = TokenType.GroupStartToken;
        tokenName = "GroupStart";
    }

}
