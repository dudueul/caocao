// name: HeartBeats$$OnApplicationPause
// entry: 016cbcf8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__OnApplicationPause(undefined8 param_1,ulong param_2)

{
  ulong uVar1;
  long lVar2;
  undefined8 uVar3;
  long *plVar4;
  long lVar5;
  undefined8 uVar6;
  long lVar7;
  long *plVar8;
  undefined8 *puVar9;
  int *piVar10;
  
  if ((bRam00000000049f97a9 & 1) == 0) {
    func_0x010c65d4(0x6a21);
    bRam00000000049f97a9 = 1;
  }
  if ((param_2 & 1) != 0) {
    return;
  }
  HeartBeats__SendHeartBeats(param_1);
  if (((*(byte *)(_class_LoadedSceneCheckUtil_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadedSceneCheckUtil_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = LoadedSceneCheckUtil__get_isWorldMap(0,0);
  if ((uVar1 & 1) != 0) {
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar2 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    uVar6 = _mm_Method_HeartBeats_OnUseOffer__;
    uVar3 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
    HttpRequest_ResponseCallback___ctor(uVar3,param_1,uVar6,0);
    plVar4 = (long *)func_0x01101ad4(_class_object___TypeInfo,6);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((_str_npsn != 0) &&
       (lVar5 = func_0x010fdfcc(_str_npsn,*(undefined8 *)(*plVar4 + 0x40)), lVar5 == 0)) {
      uVar6 = func_0x010efe90();
      func_0x010eebec(uVar6,0,0);
    }
    lVar5 = _str_npsn;
    if ((int)plVar4[3] == 0) {
      uVar6 = func_0x010ef850();
      func_0x010eebec(uVar6,0,0);
    }
    plVar4[4] = lVar5;
    if (((*(byte *)(_class_PluginManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PluginManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar5 = PluginManager__get_NPAnpsn(0,0);
    if ((lVar5 != 0) && (lVar7 = func_0x010fdfcc(lVar5,*(undefined8 *)(*plVar4 + 0x40)), lVar7 == 0)
       ) {
      uVar6 = func_0x010efe90();
      func_0x010eebec(uVar6,0,0);
    }
    if (*(uint *)(plVar4 + 3) < 2) {
      uVar6 = func_0x010ef850();
      func_0x010eebec(uVar6,0,0);
    }
    plVar4[5] = lVar5;
    if ((_str_npaCode != 0) &&
       (lVar5 = func_0x010fdfcc(_str_npaCode,*(undefined8 *)(*plVar4 + 0x40)), lVar5 == 0)) {
      uVar6 = func_0x010efe90();
      func_0x010eebec(uVar6,0,0);
    }
    lVar5 = _str_npaCode;
    if (*(uint *)(plVar4 + 3) < 3) {
      uVar6 = func_0x010ef850();
      func_0x010eebec(uVar6,0,0);
    }
    plVar4[6] = lVar5;
    lVar5 = PluginManager__get_NPACode(0,0);
    if ((lVar5 != 0) && (lVar7 = func_0x010fdfcc(lVar5,*(undefined8 *)(*plVar4 + 0x40)), lVar7 == 0)
       ) {
      uVar6 = func_0x010efe90();
      func_0x010eebec(uVar6,0,0);
    }
    if (*(uint *)(plVar4 + 3) < 4) {
      uVar6 = func_0x010ef850();
      func_0x010eebec(uVar6,0,0);
    }
    plVar4[7] = lVar5;
    if ((_str_svcId != 0) &&
       (lVar5 = func_0x010fdfcc(_str_svcId,*(undefined8 *)(*plVar4 + 0x40)), lVar5 == 0)) {
      uVar6 = func_0x010efe90();
      func_0x010eebec(uVar6,0,0);
    }
    lVar5 = _str_svcId;
    if (*(uint *)(plVar4 + 3) < 5) {
      uVar6 = func_0x010ef850();
      func_0x010eebec(uVar6,0,0);
    }
    plVar4[8] = lVar5;
    if (((*(byte *)(_class_ServerSetting_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_ServerSetting_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    plVar8 = (long *)ServerSetting__get_instance(0,0);
    if (plVar8 == (long *)0x0) {
      func_0x010eec64(0);
    }
    lVar5 = *plVar8;
    if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
      uVar1 = 0;
      piVar10 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
      do {
        if (*(long *)(piVar10 + -2) == _class_IServerSetting_TypeInfo) {
          puVar9 = (undefined8 *)(lVar5 + (ulong)(*piVar10 + 5) * 0x10 + 0x130);
          goto code_r0x016cc06c;
        }
        uVar1 = uVar1 + 1;
        piVar10 = piVar10 + 4;
      } while (uVar1 < *(ushort *)(lVar5 + 0x122));
    }
    puVar9 = (undefined8 *)func_0x010bc584(plVar8,_class_IServerSetting_TypeInfo,5);
code_r0x016cc06c:
    lVar5 = (*(code *)*puVar9)(plVar8,puVar9[1]);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar5 != 0) && (lVar7 = func_0x010fdfcc(lVar5,*(undefined8 *)(*plVar4 + 0x40)), lVar7 == 0)
       ) {
      uVar6 = func_0x010efe90();
      func_0x010eebec(uVar6,0,0);
    }
    if (*(uint *)(plVar4 + 3) < 6) {
      uVar6 = func_0x010ef850();
      func_0x010eebec(uVar6,0,0);
    }
    plVar4[9] = lVar5;
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = HttpRequest__SendAndWait(lVar2,_str__offer_useOffer,_str_POST,1,uVar3,plVar4);
    UnityEngine_MonoBehaviour__StartCoroutine(param_1,uVar6,0);
  }
  if (((*(byte *)(_class_Singleton<UserOptions>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<UserOptions>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = Singleton<WorldMapControlFlow>__get_HasInstance
                    (0,_mm_Method_Singleton<UserOptions>_get_HasInstance__);
  if ((uVar1 & 1) != 0) {
    if (((*(byte *)(_class_Singleton<UserOptions>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<UserOptions>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar2 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<UserOptions>_get_instance__);
    if (lVar2 == 0) {
      func_0x010eec64(0);
    }
    uVar1 = UserOptions__get_checkMaintenanceOnResume(lVar2,0);
    if ((uVar1 & 1) == 0) {
      return;
    }
  }
  uVar6 = _mm_Method_HeartBeats_<OnApplicationPause>m__0__;
  lVar2 = *(long *)(*(long *)(_class_HeartBeats_TypeInfo + 0xb8) + 8);
  if (lVar2 == 0) {
    uVar3 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar3,0,uVar6,0);
    *(undefined8 *)(*(long *)(_class_HeartBeats_TypeInfo + 0xb8) + 8) = uVar3;
    lVar2 = *(long *)(*(long *)(_class_HeartBeats_TypeInfo + 0xb8) + 8);
  }
  if (((*(byte *)(_class_PluginManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PluginManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam00000000049f8e08 & 1) == 0) {
    func_0x010c65d4(0xa019);
    bRam00000000049f8e08 = 1;
  }
  if (((*(byte *)(_class_Singleton<NPAController>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<NPAController>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<NPAController>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049f9427 & 1) == 0) {
    func_0x010c65d4(0x8bb1);
    bRam00000000049f9427 = 1;
  }
  lVar7 = func_0x010fdda8(_class_NPAListener_EnterToyListener_TypeInfo);
  System_Object___ctor(lVar7,0);
  *(undefined8 *)(lVar7 + 0x10) = 0;
  *(undefined8 *)(lVar7 + 0x18) = 0;
  *(undefined8 *)(lVar7 + 0x20) = 0;
  *(long *)(lVar7 + 0x28) = lVar2;
  lVar2 = *(long *)(lVar5 + 0x18);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  uVar6 = NPAListener_EnterToyListener__GetAction(lVar7);
  if (lVar2 == 0) {
    func_0x010eec64(0);
  }
  plVar4 = *(long **)(lVar2 + 0x10);
  if (plVar4 == (long *)0x0) {
    func_0x010eec64(0,uVar6,0);
  }
                    /* WARNING: Could not recover jumptable at 0x028ae860. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(*plVar4 + 0x650))(plVar4,uVar6,*(undefined8 *)(*plVar4 + 0x658));
  return;
}


