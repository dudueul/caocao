// name: BattlePlay.BattlePlayManager$$UpdateSticker
// entry: 01979da4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__UpdateSticker(long param_1)

{
  undefined4 uVar1;
  ulong uVar2;
  long lVar3;
  long lVar4;
  undefined8 uVar5;
  
  if ((bRam00000000049fae12 & 1) == 0) {
    func_0x010c65d4(0x2074);
    bRam00000000049fae12 = 1;
  }
  lVar4 = *(long *)(param_1 + 0x18);
  if (lVar4 == 0) {
    func_0x010eec64(0);
  }
  lVar4 = BattlePlay_BattlePlayData__get_sticker(lVar4);
  uVar2 = BattlePlay_BattlePlayManager__CheckStickerCondition(lVar4,lVar4);
  if ((uVar2 & 1) != 0) {
    if (((*(byte *)(_class_SceneSingleton<StickerManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<StickerManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar3 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<StickerManager>_get_instance__);
    if (lVar4 == 0) {
      func_0x010eec64(0);
      uVar5 = uRam0000000000000010;
      func_0x010eec64(0);
    }
    else {
      uVar5 = *(undefined8 *)(lVar4 + 0x10);
    }
    uVar1 = *(undefined4 *)(lVar4 + 0x18);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    StickerManager__OnStickerCallback(lVar3,uVar5,uVar1,0);
    *(undefined1 *)(lVar4 + 0x24) = 1;
  }
  return;
}


