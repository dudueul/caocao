// name: Multiplay.MultiplayManager$$OnContinueFriendlyBattle
// entry: 013a8898

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnContinueFriendlyBattle(undefined8 param_1,long param_2)

{
  long lVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  ulong uVar4;
  long *plVar5;
  long lVar6;
  undefined8 uVar7;
  undefined8 uStack_70;
  undefined8 uStack_68;
  
  if ((bRam00000000049f7b0d & 1) == 0) {
    func_0x010c65d4(0x8a4b);
    bRam00000000049f7b0d = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar1 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = LitJson_JsonData__get_Item(lVar1,_str_roomInfo,0);
  lVar1 = CompetitionManager__get_instance(0,0);
  uVar3 = func_0x010fdda8(_class_FriendlyMatchingRoom_TypeInfo);
  FriendlyMatchingRoom___ctor(uVar3,uVar2,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar1 + 0xd8) = uVar3;
  if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar1 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = UIComponents__IsExist(lVar1,_str_BattleVoiceChatInfoUI,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<UIComponents>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar1 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<UIComponents>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  lVar1 = UIComponents__get_Item(lVar1,_str_BattleVoiceChatInfoUI,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  lVar1 = UnityEngine_GameObject__GetComponent<object>
                    (lVar1,_mm_Method_UnityEngine_GameObject_GetComponent<BattleVoiceChatInfoUI>__);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
  }
  uVar4 = UnityEngine_Object__op_Inequality(0,lVar1,0,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049f98a5 & 1) == 0) {
    func_0x010c65d4(0x20eb,0);
    bRam00000000049f98a5 = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar4 = Multiplay_MultiplayManager__get_isFriendly(0,0);
  if ((uVar4 & 1) == 0) {
    return;
  }
  lVar6 = *(long *)(lVar1 + 0x18);
  lVar1 = CompetitionManager__get_instance(0,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = CompetitionManager__get_friendlyMatchingRoomId(lVar1,0);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049fa88e & 1) == 0) {
    func_0x010c65d4(0x7853,uVar2,0);
    bRam00000000049fa88e = 1;
  }
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  Multiplay_MultiplayManager__RequestFriendlyMatchingRoomInfo(lVar1,uVar2,0);
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = _mm_Method_LabelWatchMemeberCount_OnFriendlyMatchingRoomUpdatedCallback__;
  uVar7 = *(undefined8 *)(lVar1 + 0xa8);
  uVar3 = func_0x010fdda8(_class_System_Action<FriendlyMatchingRoom>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar3,lVar6,uVar2,_mm_Method_System_Action<FriendlyMatchingRoom>__ctor__);
  plVar5 = (long *)System_Delegate__Remove(0,uVar7,uVar3,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  if (plVar5 == (long *)0x0) {
code_r0x018fa78c:
    plVar5 = (long *)0x0;
  }
  else if (*plVar5 != _class_System_Action<FriendlyMatchingRoom>_TypeInfo) {
    func_0x0111ef28(&uStack_68,*(undefined8 *)(*plVar5 + 0x40));
    uVar2 = func_0x010ef81c(uStack_68);
    func_0x010eebec(uVar2,0,0);
    func_0x036457e0(&uStack_68);
    goto code_r0x018fa78c;
  }
  *(long **)(lVar1 + 0xa8) = plVar5;
  lVar1 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  uVar2 = _mm_Method_LabelWatchMemeberCount_OnFriendlyMatchingRoomUpdatedCallback__;
  uVar7 = *(undefined8 *)(lVar1 + 0xa8);
  uVar3 = func_0x010fdda8(_class_System_Action<FriendlyMatchingRoom>_TypeInfo);
  System_Action<tk2dUIUpDownHoverButton>___ctor
            (uVar3,lVar6,uVar2,_mm_Method_System_Action<FriendlyMatchingRoom>__ctor__);
  plVar5 = (long *)System_Delegate__Combine(0,uVar7,uVar3,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  if (plVar5 != (long *)0x0) {
    if (*plVar5 == _class_System_Action<FriendlyMatchingRoom>_TypeInfo) goto code_r0x018fa838;
    func_0x0111ef28(&uStack_70,*(undefined8 *)(*plVar5 + 0x40));
    uVar2 = func_0x010ef81c(uStack_70);
    func_0x010eebec(uVar2,0,0);
    func_0x036457e0(&uStack_70);
  }
  plVar5 = (long *)0x0;
code_r0x018fa838:
  *(long **)(lVar1 + 0xa8) = plVar5;
  return;
}


