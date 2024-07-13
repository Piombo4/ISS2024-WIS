%====================================================================================
% wis description   
%====================================================================================
dispatch( start_robot, start_robot(X) ).
request( moverobot, moverobot(TARGETX,TARGETY) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_basic_robot, "localhost",  "TCP", "8020").
 qactor( basic_robot, ctx_basic_robot, "external").
  qactor( oprobot, ctx_wis, "it.unibo.oprobot.Oprobot").
 static(oprobot).
