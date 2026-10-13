// name: BattlePlay.BattlePlayManager$$InitControls
// entry: 01975a2c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__InitControls(long param_1)

{
  long lVar1;
  
  if ((bRam00000000049fae02 & 1) == 0) {
    func_0x010c65d4(0x206c);
    bRam00000000049fae02 = 1;
  }
  lVar1 = *(long *)(param_1 + 0x20);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Clear
            (lVar1,_mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Clear__);
  *(undefined8 *)(param_1 + 0x30) = 0;
  return;
}


