grammar Expr;

prog
  :
  stat+
  ;

stat
  :
  expr NEWLINE
  | ID '=' expr NEWLINE
  | NEWLINE
  ;

expr
  :
  e=multExpr
  (
    '+' e=multExpr
    | '-' e=multExpr
  )*
  ;

multExpr
  :
  e=atom ('*' e=atom)*
  ;

atom
  :
  INT
  | ID
  | '(' expr ')'
  ;

ID
  :
  (
    'a'..'z'
    | 'A'..'Z'
  )+
  ;

INT
  :
  '0'..'9'+
  ;

NEWLINE
  :
  '\r'? '\n'
  ;

WS
  :
  (
    ' '
    | '\t'
  )+
  
  {
   $channel = HIDDEN;
  }
  ;
