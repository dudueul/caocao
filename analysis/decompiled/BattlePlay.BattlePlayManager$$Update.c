// name: BattlePlay.BattlePlayManager$$Update
// entry: 01977a2c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__Update(long param_1)

{
  undefined4 uVar1;
  ulong uVar2;
  long lVar3;
  long lVar4;
  undefined8 uVar5;
  
  if ((bRam00000000049fae10 & 1) == 0) {
    func_0x010c65d4(0x2075);
    bRam00000000049fae10 = 1;
  }
  if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
  if ((uVar2 & 1) != 0) {
    if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar3 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    uVar2 = Multiplay_MultiplayManager__IsStep(lVar3,6,0);
    if ((uVar2 & 1) != 0) {
      BattlePlay_BattlePlayManager__UpdateControl(param_1);
      if ((bRam00000000049fae12 & 1) == 0) {
        func_0x010c65d4(0x2074);
        bRam00000000049fae12 = 1;
      }
      lVar3 = *(long *)(param_1 + 0x18);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      lVar3 = BattlePlay_BattlePlayData__get_sticker(lVar3);
      uVar2 = BattlePlay_BattlePlayManager__CheckStickerCondition(lVar3,lVar3);
      if ((uVar2 & 1) != 0) {
        if (((*(byte *)(_class_SceneSingleton<StickerManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<StickerManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar4 = SceneSingleton<WorldBossBattleController>__get_instance
                          (0,_mm_Method_SceneSingleton<StickerManager>_get_instance__);
        if (lVar3 == 0) {
          func_0x010eec64(0);
          uVar5 = uRam0000000000000010;
          func_0x010eec64(0);
        }
        else {
          uVar5 = *(undefined8 *)(lVar3 + 0x10);
        }
        uVar1 = *(undefined4 *)(lVar3 + 0x18);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        StickerManager__OnStickerCallback(lVar4,uVar5,uVar1,0);
        *(undefined1 *)(lVar3 + 0x24) = 1;
      }
      return;
    }
  }
  return;
}


