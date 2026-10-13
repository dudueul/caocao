// name: Multiplay.MultiplayManager$$OnInvitedVoiceChat
// entry: 013ad36c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnInvitedVoiceChat(long param_1,long param_2)

{
  uint uVar1;
  ulong uVar2;
  undefined8 uVar3;
  long lVar4;
  undefined8 uVar5;
  long *plVar6;
  long lVar7;
  long lVar8;
  
  if ((bRam00000000049f7b28 & 1) == 0) {
    func_0x010c65d4(0x8a58);
    bRam00000000049f7b28 = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar2 & 1) != 0) {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar1 = Multiplay_MultiplayManager__get_isFriendly();
    if ((uVar1 & 1) == 0) {
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Util_TypeInfo);
      }
      lVar4 = Util__GetData(0,uVar3,_str_roomHostUid,0,0);
      if ((lVar4 != 0) && (*(long *)(param_1 + 0x148) == lVar4)) {
        if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar4 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        uVar2 = VoiceChatController__get_IsConnected(lVar4,0);
        if ((uVar2 & 1) != 0) {
          if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar4 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
          uVar3 = _mm_Method_Multiplay_MultiplayManager_OnVoiceJoinRoom__;
          uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
          System_Action___ctor(uVar5,param_1,uVar3,0);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          VoiceChatController__remove_LeftTheRoom(lVar4,uVar5,0);
          lVar4 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
          uVar3 = _mm_Method_Multiplay_MultiplayManager_OnVoiceJoinRoom__;
          uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
          System_Action___ctor(uVar5,param_1,uVar3,0);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          VoiceChatController__add_LeftTheRoom(lVar4,uVar5,0);
          lVar4 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          if ((bRam00000000049fdc23 & 1) == 0) {
            func_0x010c65d4(0xfe9b,0);
            bRam00000000049fdc23 = 1;
          }
          uVar2 = VoiceChatController__get_IsConnected(lVar4);
          if ((uVar2 & 1) != 0) {
            if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar8 = *(long *)(lVar4 + 0x40);
            *(undefined8 *)(lVar4 + 0x18) = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
            if (lVar8 == 0) {
              func_0x010eec64(0);
            }
            *(undefined4 *)(lVar8 + 0x10) = 0;
          }
          return;
        }
        if ((bRam00000000049f7b29 & 1) == 0) {
          func_0x010c65d4(0x8a70);
          bRam00000000049f7b29 = 1;
        }
        if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar4 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        VoiceChatController__Mute(lVar4,1,0);
        lVar4 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
        plVar6 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
        lVar8 = func_0x010fdd08(_class_ulong_TypeInfo,&stack0xffffffffffffffb8);
        if (plVar6 == (long *)0x0) {
          func_0x010eec64(0);
        }
        if ((lVar8 != 0) &&
           (lVar7 = func_0x010fdfcc(lVar8,*(undefined8 *)(*plVar6 + 0x40)), lVar7 == 0)) {
          uVar3 = func_0x010efe90();
          func_0x010eebec(uVar3,0,0);
        }
        if ((int)plVar6[3] == 0) {
          uVar3 = func_0x010ef850();
          func_0x010eebec(uVar3,0,0);
        }
        plVar6[4] = lVar8;
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        VoiceChatController__JoinRoom(lVar4,1,plVar6,0);
        lVar4 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
        uVar3 = _mm_Method_Multiplay_MultiplayManager_OnVoiceJoinRoom__;
        uVar5 = func_0x010fdda8(_class_System_Action_TypeInfo);
        System_Action___ctor(uVar5,param_1,uVar3,0);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        VoiceChatController__remove_LeftTheRoom(lVar4,uVar5,0);
        return;
      }
    }
  }
  return;
}


