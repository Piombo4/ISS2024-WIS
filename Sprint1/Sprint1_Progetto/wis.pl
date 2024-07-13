%====================================================================================
% wis description   
%====================================================================================
dispatch( start_robot, start_robot(X) ).
dispatch( get_waste, get_waste(N) ).
dispatch( burn_in, burn_in(X) ).
dispatch( burn_out, burn_out(X) ).
dispatch( ash_out, ash_out(N) ).
request( moverobot, moverobot(TARGETX,TARGETY) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_basic_robot, "localhost",  "TCP", "8020").
 qactor( basic_robot, ctx_basic_robot, "external").
  qactor( waste_storage, ctx_wis, "external").
  qactor( ash_storage, ctx_wis, "external").
  qactor( incinerator, ctx_wis, "external").
  qactor( oprobot, ctx_wis, "it.unibo.oprobot.Oprobot").
 static(oprobot).
