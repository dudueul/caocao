// name: BattlePlay.BattlePlayManager$$InitBattleData
// entry: 019759b0

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__InitBattleData
               (long param_1,undefined8 param_2,undefined8 param_3)

{
  undefined8 uVar1;
  long lVar2;
  
  if ((bRam00000000049fae01 & 1) == 0) {
    func_0x010c65d4(0x206b);
    bRam00000000049fae01 = 1;
  }
  *(undefined4 *)(param_1 + 0x48) = 0;
  uVar1 = func_0x010fdda8(_class_BattlePlay_BattlePlayData_TypeInfo);
  BattlePlay_BattlePlayData___ctor(uVar1,param_2,param_3);
  *(undefined8 *)(param_1 + 0x18) = uVar1;
  if ((bRam00000000049fae02 & 1) == 0) {
    func_0x010c65d4(0x206c);
    bRam00000000049fae02 = 1;
  }
  lVar2 = *(long *)(param_1 + 0x20);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Clear
            (lVar2,_mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Clear__);
  *(undefined8 *)(param_1 + 0x30) = 0;
  return;
}


