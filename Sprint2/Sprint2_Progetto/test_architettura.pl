%====================================================================================
% test_architettura description   
%====================================================================================
dispatch( waste_in, waste_in(X) ).
dispatch( waste_qty, waste_qty(X) ).
dispatch( get_waste, get_waste(X) ).
dispatch( deposit_ash, deposit_ash(X) ).
request( empty_ash, empty_ash(X) ).
reply( ashes_taken, ashes_taken(LEVEL) ).  %%for empty_ash
request( start_test, start_test(N) ).
dispatch( start_robot, start_robot(N) ).
dispatch( qty, qty(N) ).
reply( successful, successful(X) ).  %%for start_test
reply( fail, fail(X) ).  %%for start_test
%====================================================================================
context(ctx_wis, "127.0.0.1",  "TCP", "8014").
context(ctx_test, "localhost",  "TCP", "8018").
 qactor( waste_storage, ctx_wis, "external").
  qactor( ash_storage, ctx_wis, "external").
  qactor( test_manager, ctx_test, "it.unibo.test_manager.Test_manager").
 static(test_manager).
  qactor( test_manager_ash, ctx_test, "it.unibo.test_manager_ash.Test_manager_ash").
 static(test_manager_ash).
