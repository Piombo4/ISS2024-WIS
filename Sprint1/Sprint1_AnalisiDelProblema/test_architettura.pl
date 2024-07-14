%====================================================================================
% test_architettura description   
%====================================================================================
request( start_test, start_test(N) ).
dispatch( start_robot, start_robot(N) ).
dispatch( qty, qty(N) ).
%====================================================================================
context(ctx_wastestorage, "127.0.0.1",  "TCP", "8015").
context(ctx_ashstorage, "127.0.0.2",  "TCP", "8016").
context(ctx_oprobot, "127.0.0.3",  "TCP", "8017").
context(ctx_test, "localhost",  "TCP", "8018").
 qactor( waste_storage, ctx_wastestorage, "external").
  qactor( ash_storage, ctx_ashstorage, "external").
  qactor( op_robot, ctx_oprobot, "external").
  qactor( test_manager, ctx_test, "it.unibo.test_manager.Test_manager").
 static(test_manager).
  qactor( test_manager_ash, ctx_test, "it.unibo.test_manager_ash.Test_manager_ash").
 static(test_manager_ash).
