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
%====================================================================================
context(ctx_wastestorage, "localhost",  "TCP", "8016").
context(ctx_oprobot, "127.0.0.1",  "TCP", "8017").
context(ctx_mock_external_entity, "127.0.0.2",  "TCP", "8018").
 qactor( op_robot, ctx_oprobot, "external").
  qactor( mock_external_entity, ctx_mock_external_entity, "external").
  qactor( waste_storage, ctx_wastestorage, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
