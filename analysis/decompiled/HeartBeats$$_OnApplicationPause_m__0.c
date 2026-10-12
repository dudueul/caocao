// name: HeartBeats$$<OnApplicationPause>m__0
// entry: 016cc56c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__<OnApplicationPause>m__0(void)

{
  long lVar1;
  undefined8 uVar2;
  ulong uVar3;
  long *plVar4;
  undefined8 uVar5;
  code *pcVar6;
  undefined8 uVar7;
  
  if ((bRam00000000049f97ab & 1) == 0) {
    func_0x010c65d4(0x6a26);
    bRam00000000049f97ab = 1;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam00000000049f76b8 & 1) == 0) {
    func_0x010c65d4(0xfab7,0);
    bRam00000000049f76b8 = 1;
  }
  if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
  }
  uVar3 = UnityEngine_Object__op_Equality(0,0,uVar2,0);
  if ((uVar3 & 1) == 0) {
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar1 = UnityEngine_Object__Instantiate<object>
                      (0,uVar2,_mm_Method_UnityEngine_Object_Instantiate<GameObject>__);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    plVar4 = (long *)UnityEngine_GameObject__AddComponent<object>
                               (lVar1,
                                _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                               );
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    CaocaoUIModalPopup__set_DepthGrade(plVar4,1000,0);
    uVar2 = ___L(0,_str_점검_중,0);
    uVar7 = ___L(0,_str_현재_점검_중_입니다_,0);
    uVar5 = func_0x01101ad4(_class_object___TypeInfo,0);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    PanelPopup1BtnConfirm__Apply(plVar4,uVar2,uVar7,uVar5,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = _mm_Method_Util_<ShowMaintenancePopup>m__B__;
    if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x3d0) == 0) {
      uVar7 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
      System_Func<PanelPopupBase_PopupState>___ctor
                (uVar7,0,uVar2,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x3d0) = uVar7;
    }
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x3d0);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
      PanelPopup2Buttons__add_OnConfirm(0,uVar2,0);
      func_0x010eec64(0);
    }
    else {
      PanelPopup2Buttons__add_OnConfirm(plVar4,uVar2,0);
    }
                    /* WARNING: Could not recover jumptable at 0x01315560. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (**(code **)(*plVar4 + 0x2c0))(plVar4,*(undefined8 *)(*plVar4 + 0x2c8));
    return;
  }
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam00000000049f762b & 1) == 0) {
    func_0x010c65d4(0xfa95);
    bRam00000000049f762b = 1;
  }
  if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = Singleton<WorldMapControlFlow>__get_HasInstance
                    (0,_mm_Method_Singleton<HttpRequest>_get_HasInstance__);
  if ((uVar3 & 1) != 0) {
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar1 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    HttpRequest__Clear(lVar1,0);
  }
  lVar1 = UserDataSet__get_instance(0,0);
  if (lVar1 != 0) {
    lVar1 = UserDataSet__get_instance(0,0);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    UserDataSet__DestroyMyUserData(lVar1,0);
  }
  if (((*(byte *)(_class_Singleton<CentralTimer>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CentralTimer>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = Singleton<WorldMapControlFlow>__get_HasInstance
                    (0,_mm_Method_Singleton<CentralTimer>_get_HasInstance__);
  if ((uVar3 & 1) != 0) {
    if (((*(byte *)(_class_Singleton<CentralTimer>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CentralTimer>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar1 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CentralTimer>_get_instance__);
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    uVar2 = UnityEngine_Component__get_gameObject(lVar1,0);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
    }
    UnityEngine_Object__DestroyImmediate(0,uVar2,0);
  }
  if (((*(byte *)(_class_GuildManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_GuildManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  GuildManager__Clear(0,0);
  uVar2 = _str_Restart;
  pcVar6 = pcRam0000000004a04680;
  if ((pcRam0000000004a04680 == (code *)0x0) &&
     (pcVar6 = (code *)func_0x0111e49c(&UNK_039a46a0), pcVar6 == (code *)0x0)) {
    uVar7 = func_0x010f000c(&UNK_039a46a0);
    func_0x010eebec(uVar7,0,0);
  }
  pcRam0000000004a04680 = pcVar6;
                    /* WARNING: Could not recover jumptable at 0x02acbc48. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04680)(uVar2,0xffffffff,0,1);
  return;
}


