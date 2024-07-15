%====================================================================================
% wis description   
%====================================================================================
dispatch( start_robot, start_robot(X) ).
dispatch( waiting, waiting(X) ).
dispatch( waste_in, waste_in(X) ).
dispatch( waste_qty, waste_qty(X) ).
dispatch( get_waste, get_waste(X) ).
dispatch( turn_on, turn_on(X) ).
dispatch( burn_in, burn_in(X) ).
dispatch( burn_start, burn_start(X) ).
dispatch( burn_end, burn_end(X) ).
dispatch( get_ash, get_ash(X) ).
dispatch( deposit_ash, deposit_ash(X) ).
request( empty_ash, empty_ash(X) ).
request( moverobot, moverobot(TARGETX,TARGETY) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_basic_robot, "localhost",  "TCP", "8020").
 qactor( basic_robot, ctx_basic_robot, "external").
  qactor( waste_storage, ctx_wis, "external").
  qactor( ash_storage, ctx_wis, "external").
  qactor( incinerator, ctx_wis, "external").
  qactor( wis, ctx_wis, "external").
  qactor( oprobot, ctx_wis, "it.unibo.oprobot.Oprobot").
 static(oprobot).
