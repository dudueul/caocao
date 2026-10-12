// name: Multiplay.MultiplayManager.<CancelMatchmaking>c__AnonStorey4$$<>m__1
// entry: 01f5f08c

undefined8 Multiplay_MultiplayManager_<CancelMatchmaking>c__AnonStorey4__<>m__1(long param_1)

{
  uint uVar1;
  long lVar2;
  long lVar3;
  
  lVar3 = *(long *)(param_1 + 0x18);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  lVar2 = Multiplay_MultiplayManager__get_battlePlayData(lVar3,0);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  uVar1 = BattlePlay_BattlePlayData__get_friendly(lVar2,0);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  Multiplay_MultiplayManager__SendCancelMatchmaking(lVar3,0,~uVar1 & 1,0);
  return 1;
}


