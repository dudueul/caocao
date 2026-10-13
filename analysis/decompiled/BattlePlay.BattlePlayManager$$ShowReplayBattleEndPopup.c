// name: BattlePlay.BattlePlayManager$$ShowReplayBattleEndPopup
// entry: 0197a36c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long BattlePlay_BattlePlayManager__ShowReplayBattleEndPopup(undefined8 param_1)

{
  long lVar1;
  
  if ((bRam00000000049fae17 & 1) == 0) {
    func_0x010c65d4(0x2070);
    bRam00000000049fae17 = 1;
  }
  lVar1 = func_0x010fdda8(
                         _class_BattlePlay_BattlePlayManager_<ShowReplayBattleEndPopup>c__Iterator0_TypeInfo
                         );
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar1 + 0x18) = param_1;
  return lVar1;
}


