%====================================================================================
% wastestorage description   
%====================================================================================
dispatch( get_waste, get_waste(N) ).
dispatch( waste_in, waste_in(N) ).
%====================================================================================
context(ctx_wastestorage, "localhost",  "TCP", "8016").
context(ctx_oprobot, "127.0.0.1",  "TCP", "8017").
context(ctx_mock_external_entity, "127.0.0.2",  "TCP", "8018").
 qactor( op_robot, ctx_oprobot, "external").
  qactor( mock_external_entity, ctx_mock_external_entity, "external").
  qactor( waste_storage, ctx_wastestorage, "it.unibo.waste_storage.Waste_storage").
 static(waste_storage).
