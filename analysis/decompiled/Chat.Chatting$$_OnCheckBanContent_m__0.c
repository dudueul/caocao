// name: Chat.Chatting$$<OnCheckBanContent>m__0
// entry: 01d31fa8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Chat_Chatting__<OnCheckBanContent>m__0(undefined8 param_1,undefined8 param_2)

{
  bool bVar1;
  undefined8 uVar2;
  long *plVar3;
  long *plVar4;
  long lVar5;
  ulong uVar6;
  undefined8 uVar7;
  long lVar8;
  long lVar9;
  undefined8 uVar10;
  long *plVar11;
  long *plVar12;
  undefined8 uVar13;
  undefined1 auStack_70 [16];
  
  if ((bRam00000000049fcf63 & 1) == 0) {
    func_0x010c65d4(0x25f9);
    bRam00000000049fcf63 = 1;
  }
  lVar9 = MyUserData__get_instance(0,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = UserData__get_banContents(lVar9,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  ITransmittable__Deserialize(lVar9,param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam00000000049f766c & 1) == 0) {
    func_0x010c65d4(0xfab8,1,0);
    bRam00000000049f766c = 1;
  }
  auStack_70._0_8_ = 0;
  auStack_70._8_8_ = 0;
  lVar9 = MyUserData__get_instance(0,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = UserData__get_banContents(lVar9,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  lVar9 = UserBanContents__get_Item(lVar9,1,0);
  if (lVar9 != 0) {
    if (((*(byte *)(_class_PrefabsLib_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_PrefabsLib_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = PrefabsLib__get_m_common_goods_popupBtn2(0,0);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar10 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
    lVar9 = _class_Util_TypeInfo;
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      lVar9 = func_0x010da55c();
    }
    lVar9 = Util__InstantiateOnUIRoot(lVar9,uVar2,0,uVar10,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    plVar3 = (long *)UnityEngine_GameObject__AddComponent<object>
                               (lVar9,
                                _mm_Method_UnityEngine_GameObject_AddComponent<PanelPopup1BtnConfirm>__
                               );
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
      CaocaoUIModalPopup__set_DepthGrade(0,1000,0);
      func_0x010eec64(0);
      plVar4 = (long *)0x0;
    }
    else {
      CaocaoUIModalPopup__set_DepthGrade(plVar3,1000,0);
      plVar4 = plVar3;
    }
    uVar2 = CaocaoUIModalPopup__set_IgnoreTappingOutSide(plVar4,1,0);
    uVar2 = Util__GetBanContentsTitle(uVar2,1);
    plVar4 = (long *)func_0x01101ad4(_class_object___TypeInfo,5);
    uVar10 = ___L(0,
                  _str_제한_관련_문의는__넥슨닷컴>고객센터>삼국지조조전_Online_으로_문의_부탁드립니다_
                  ,0);
    if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Localization_TypeInfo);
    }
    lVar9 = Localization__Get(0,uVar10,0);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar9 != 0) && (lVar5 = func_0x010fdfcc(lVar9,*(undefined8 *)(*plVar4 + 0x40)), lVar5 == 0)
       ) {
      uVar10 = func_0x010efe90();
      func_0x010eebec(uVar10,0,0);
    }
    if ((int)plVar4[3] == 0) {
      uVar10 = func_0x010ef850();
      func_0x010eebec(uVar10,0,0);
    }
    plVar4[4] = lVar9;
    uVar10 = ___L(0,_str_제한_사유,0);
    lVar9 = Localization__Get(0,uVar10,0);
    if ((lVar9 != 0) && (lVar5 = func_0x010fdfcc(lVar9,*(undefined8 *)(*plVar4 + 0x40)), lVar5 == 0)
       ) {
      uVar10 = func_0x010efe90();
      func_0x010eebec(uVar10,0,0);
    }
    if (*(uint *)(plVar4 + 3) < 2) {
      uVar10 = func_0x010ef850();
      func_0x010eebec(uVar10,0,0);
    }
    plVar4[5] = lVar9;
    lVar9 = MyUserData__get_instance(0,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    lVar9 = UserData__get_banContents(lVar9,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    lVar9 = UserBanContents__get_Item(lVar9,1,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    lVar9 = UserBanContent__get_comment(lVar9,0);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar9 != 0) && (lVar5 = func_0x010fdfcc(lVar9,*(undefined8 *)(*plVar4 + 0x40)), lVar5 == 0)
       ) {
      uVar10 = func_0x010efe90();
      func_0x010eebec(uVar10,0,0);
    }
    if (*(uint *)(plVar4 + 3) < 3) {
      uVar10 = func_0x010ef850();
      func_0x010eebec(uVar10,0,0);
    }
    plVar4[6] = lVar9;
    uVar10 = ___L(0,_str_해제일,0);
    lVar9 = Localization__Get(0,uVar10,0);
    if ((lVar9 != 0) && (lVar5 = func_0x010fdfcc(lVar9,*(undefined8 *)(*plVar4 + 0x40)), lVar5 == 0)
       ) {
      uVar10 = func_0x010efe90();
      func_0x010eebec(uVar10,0,0);
    }
    if (*(uint *)(plVar4 + 3) < 4) {
      uVar10 = func_0x010ef850();
      func_0x010eebec(uVar10,0,0);
    }
    plVar4[7] = lVar9;
    lVar9 = MyUserData__get_instance(0,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    lVar9 = UserData__get_banContents(lVar9,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = UserBanContents__IsUnlimitedBan(lVar9,1,0);
    uVar10 = _DAT_049e5a10;
    bVar1 = (uVar6 & 1) == 0;
    plVar12 = (long *)0x0;
    if (bVar1) {
      plVar12 = plVar3;
    }
    uVar7 = 0;
    if (bVar1) {
      uVar7 = uVar2;
    }
    plVar11 = (long *)0x0;
    if (bVar1) {
      plVar11 = plVar4;
    }
    lVar9 = 0;
    if (bVar1) {
      lVar9 = 4;
    }
    uVar13 = 0;
    if (bVar1) {
      uVar13 = _DAT_049e5a10;
    }
    if ((uVar6 & 1) == 0) {
      lVar5 = MyUserData__get_instance(0,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      lVar5 = UserData__get_banContents(lVar5,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      lVar5 = UserBanContents__get_Item(lVar5,1,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      auStack_70 = UserBanContent__get_banDate(lVar5,0);
      lVar5 = func_0x02de6ee8(auStack_70,_str_yyyy_MM_dd_HH_mm_ss,0);
    }
    else {
      uVar7 = ___L(0,_str_영구_이용_제한,0);
      if (((*(byte *)(_class_Localization_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Localization_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Localization_TypeInfo);
      }
      lVar5 = Localization__Get(0,uVar7,0);
      lVar9 = 4;
      uVar7 = uVar2;
      plVar11 = plVar4;
      plVar12 = plVar3;
      uVar13 = uVar10;
    }
    if (plVar11 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar5 != 0) &&
       (lVar8 = func_0x010fdfcc(lVar5,*(undefined8 *)(*plVar11 + 0x40)), lVar8 == 0)) {
      uVar2 = func_0x010efe90();
      func_0x010eebec(uVar2,0,0);
    }
    if (*(uint *)(plVar11 + 3) <= (uint)lVar9) {
      uVar2 = func_0x010ef850();
      func_0x010eebec(uVar2,0,0);
    }
    plVar11[lVar9 + 4] = lVar5;
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = System_String__Format(0,uVar13,plVar11,0);
    uVar10 = func_0x01101ad4(_class_object___TypeInfo,0);
    if (plVar12 == (long *)0x0) {
      func_0x010eec64(0);
    }
    PanelPopup1BtnConfirm__Apply(plVar12,uVar7,uVar2,uVar10,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = _mm_Method_Util_<ShowPopupBan>m__9__;
    if (*(long *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x3b0) == 0) {
      uVar10 = func_0x010fdda8(_class_System_Func<PanelPopupBase_PopupState>_TypeInfo);
      System_Func<PanelPopupBase_PopupState>___ctor
                (uVar10,0,uVar2,_mm_Method_System_Func<PanelPopupBase_PopupState>__ctor__);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x3b0) = uVar10;
    }
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = *(undefined8 *)(*(long *)(_class_Util_TypeInfo + 0xb8) + 0x3b0);
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
      PanelPopup2Buttons__add_OnConfirm(0,uVar2,0);
      func_0x010eec64(0);
    }
    else {
      PanelPopup2Buttons__add_OnConfirm(plVar3,uVar2,0);
    }
    (**(code **)(*plVar3 + 0x2c0))(plVar3,*(undefined8 *)(*plVar3 + 0x2c8));
  }
  return;
}


