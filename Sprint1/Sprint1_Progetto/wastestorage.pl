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
request( moverobot, moverobot(TARGETX,TARGETY) ).
dispatch( get_waste, get_waste(N) ).
dispatch( waste_in, waste_in(N) ).
dispatch( scaleinfo, scaleinfo(N) ).
%====================================================================================
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
context(ctx_wis, "localhost",  "TCP", "8016").
 qactor( waste_storage, ctx_wis, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
=======
>>>>>>> Stashed changes
context(ctx_wis, "localhost",  "TCP", "8014").
 qactor( op_robot, ctx_wis, "external").
  qactor( wis, ctx_wis, "external").
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
<<<<<<< Updated upstream
=======
>>>>>>> 8a1182f86cecf3964817e3a9013508f28a28be34
>>>>>>> Stashed changes
