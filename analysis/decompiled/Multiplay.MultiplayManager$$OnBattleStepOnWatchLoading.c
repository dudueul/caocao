// name: Multiplay.MultiplayManager$$OnBattleStepOnWatchLoading
// entry: 013a5964

/* WARNING: Removing unreachable block (ram,0x013a5b40) */
/* WARNING: Removing unreachable block (ram,0x013a5b44) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnBattleStepOnWatchLoading(long param_1,long param_2)

{
  uint uVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uStack_58;
  undefined8 uStack_50;
  undefined8 uStack_48;
  
  if ((bRam00000000049f7af6 & 1) == 0) {
    func_0x010c65d4(0x8a47);
    bRam00000000049f7af6 = 1;
  }
  uStack_50 = 0;
  uStack_48 = 0;
  uStack_58 = 0;
  if (*(char *)(param_1 + 0x141) != '\0') {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = Multiplay_MultiplayManager__get_isWatchingBattle();
    if ((uVar2 & 1) != 0) {
      if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar2 = LoadedSceneCheckUtil__get_isBattleMap(0,0);
      if ((uVar2 & 1) != 0) {
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        uVar3 = SocketIO_SocketIOEvent__get_data(param_2,0);
        if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_Util_TypeInfo);
        }
        uVar1 = Util__GetData(0,uVar3,_str_isOnBattle,1,0);
        if ((uVar1 & 1) == 0) {
          lVar4 = *(long *)(param_1 + 0x48);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          lVar4 = BattlePlay_BattlePlayData__get_controls(lVar4,0);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_List<object>__GetEnumerator
                    (&uStack_58,lVar4,
                     _mm_Method_System_Collections_Generic_List<BattleControl>_GetEnumerator__);
          while (uVar1 = func_0x027e6418(&uStack_58,
                                         _mm_Method_System_Collections_Generic_List_Enumerator<BattleControl>_MoveNext__
                                        ), (uVar1 & 1) != 0) {
            lVar4 = func_0x027e64f4(&uStack_58,
                                    _mm_Method_System_Collections_Generic_List_Enumerator<BattleControl>_get_Current__
                                   );
            if (lVar4 == 0) {
              func_0x010eec64(0);
            }
            *(undefined1 *)(lVar4 + 0x54) = 1;
          }
          func_0x027e6294(&uStack_58,
                          _mm_Method_System_Collections_Generic_List_Enumerator<BattleControl>_Dispose__
                         );
          if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) = 1;
          if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar4 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          BattlePlay_BattlePlayManager__FinishWatchLoading(lVar4,0);
        }
      }
    }
  }
  return;
}


