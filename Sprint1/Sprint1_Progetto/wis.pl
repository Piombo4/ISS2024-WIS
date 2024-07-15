%====================================================================================
% wis description   
%====================================================================================
dispatch( burning, burning(N) ).
dispatch( waste_qty, waste_qty(N) ).
dispatch( ash_qty, ash_qty(N) ).
dispatch( burn_out, burn_out(N) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_oprobot, "127.0.0.1",  "TCP", "8017").
 qactor( op_robot, ctx_oprobot, "external").
  qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
