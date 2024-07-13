%====================================================================================
% ashstorage description   
%====================================================================================
dispatch( get_ash, get_ash(N) ).
dispatch( ash_out, ash_out(N) ).
%====================================================================================
context(ctx_ashstorage, "localhost",  "TCP", "8019").
context(ctx_oprobot, "127.0.0.1",  "TCP", "8017").
context(ctx_mock_external_entity, "127.0.0.2",  "TCP", "8018").
 qactor( op_robot, ctx_oprobot, "external").
  qactor( mock_external_entity, ctx_mock_external_entity, "external").
  qactor( ash_storage, ctx_ashstorage, "it.unibo.ash_storage.Ash_storage").
 static(ash_storage).
