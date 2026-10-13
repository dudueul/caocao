// name: BattlePlay.BattlePlayManager$$CheckBattleStepOnWatch
// entry: 0197b098

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 BattlePlay_BattlePlayManager__CheckBattleStepOnWatch(void)

{
  undefined8 uVar1;
  
  if ((bRam00000000049fae18 & 1) == 0) {
    func_0x010c65d4(0x205f);
    bRam00000000049fae18 = 1;
  }
  uVar1 = func_0x010fdda8(
                         _class_BattlePlay_BattlePlayManager_<CheckBattleStepOnWatch>c__Iterator1_TypeInfo
                         );
  System_Object___ctor(uVar1,0);
  return uVar1;
}


