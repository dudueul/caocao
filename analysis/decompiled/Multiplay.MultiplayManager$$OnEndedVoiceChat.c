// name: Multiplay.MultiplayManager$$OnEndedVoiceChat
// entry: 013ad7c4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnEndedVoiceChat(long param_1,long param_2)

{
  ulong uVar1;
  undefined8 uVar2;
  long lVar3;
  long lVar4;
  
  if ((bRam00000000049f7b2a & 1) == 0) {
    func_0x010c65d4(0x8a4e);
    bRam00000000049f7b2a = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar1 & 1) != 0) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar2 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    lVar3 = Util__GetData(0,uVar2,_str_roomHostUid,0,0);
    if (*(long *)(param_1 + 0x148) == lVar3) {
      if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar3 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam00000000049fdc23 & 1) == 0) {
        func_0x010c65d4(0xfe9b,0);
        bRam00000000049fdc23 = 1;
      }
      uVar1 = VoiceChatController__get_IsConnected(lVar3);
      if ((uVar1 & 1) != 0) {
        if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar4 = *(long *)(lVar3 + 0x40);
        *(undefined8 *)(lVar3 + 0x18) = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
        if (lVar4 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar4 + 0x10) = 0;
      }
      return;
    }
  }
  return;
}


