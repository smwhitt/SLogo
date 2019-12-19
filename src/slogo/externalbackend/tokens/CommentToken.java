package slogo.externalbackend.tokens;

import slogo.externalbackend.TokenType;

/**
 * @author Jerry Wang
 */

public class CommentToken extends Token{

    public CommentToken(){
        tokenType = TokenType.CommentToken;
        tokenName = "Comment";
    }

}
