// name: Multiplay.MultiplayManager$$OnVoiceJoinRoom
// entry: 013ad5f8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnVoiceJoinRoom(long param_1)

{
  long lVar1;
  long *plVar2;
  long lVar3;
  long lVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uStack_48;
  
  if ((bRam00000000049f7b29 & 1) == 0) {
    func_0x010c65d4(0x8a70);
    bRam00000000049f7b29 = 1;
  }
  if (((*(byte *)(_class_Singleton<VoiceChatController>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<VoiceChatController>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  VoiceChatController__Mute(lVar1,1,0);
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
  plVar2 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
  uStack_48 = *(undefined8 *)(param_1 + 0x148);
  lVar3 = func_0x010fdd08(_class_ulong_TypeInfo,&uStack_48);
  if (plVar2 == (long *)0x0) {
    func_0x010eec64(0);
  }
  if ((lVar3 != 0) && (lVar4 = func_0x010fdfcc(lVar3,*(undefined8 *)(*plVar2 + 0x40)), lVar4 == 0))
  {
    uVar5 = func_0x010efe90();
    func_0x010eebec(uVar5,0,0);
  }
  if ((int)plVar2[3] == 0) {
    uVar5 = func_0x010ef850();
    func_0x010eebec(uVar5,0,0);
  }
  plVar2[4] = lVar3;
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  VoiceChatController__JoinRoom(lVar1,1,plVar2,0);
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<VoiceChatController>_get_instance__);
  uVar5 = _mm_Method_Multiplay_MultiplayManager_OnVoiceJoinRoom__;
  uVar6 = func_0x010fdda8(_class_System_Action_TypeInfo);
  System_Action___ctor(uVar6,param_1,uVar5,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  VoiceChatController__remove_LeftTheRoom(lVar1,uVar6,0);
  return;
}


