%====================================================================================
% wastestorage description   
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
reply( ashes_taken, ashes_taken(LEVEL) ).  %%for empty_ash
request( moverobot, moverobot(TARGETX,TARGETY) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
 qactor( op_robot, ctx_wis, "external").
  qactor( wis, ctx_wis, "external").
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
