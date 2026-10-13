// name: FinishScenario.<SendScenarioBattleEnd>c__Iterator1$$Reset
// entry: 02049334

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 FinishScenario_<SendScenarioBattleEnd>c__Iterator1__Reset(void)

{
  int iVar1;
  int iVar2;
  char cVar3;
  undefined4 uVar4;
  undefined8 uVar5;
  long lVar6;
  long lVar7;
  long lVar8;
  undefined8 uVar9;
  long *plVar10;
  long lVar11;
  long lVar12;
  ulong uVar13;
  undefined8 uVar14;
  undefined4 uStack_64;
  
  if ((bRam00000000049fea40 & 1) == 0) {
    func_0x010c65d4(0xdfdd);
    bRam00000000049fea40 = 1;
  }
  uVar5 = func_0x010fdda8(_class_System_NotSupportedException_TypeInfo);
  System_NotSupportedException___ctor(uVar5,0);
  func_0x010eebec(uVar5,0,_mm_Method_FinishScenario_<SendScenarioBattleEnd>c__Iterator1_Reset__);
  lVar6 = func_0x0112148c();
  if ((bRam00000000049fea41 & 1) == 0) {
    func_0x010c65d4(0xdfde);
    bRam00000000049fea41 = 1;
  }
  iVar2 = *(int *)(lVar6 + 0x34);
  *(undefined4 *)(lVar6 + 0x34) = 0xffffffff;
  iVar1 = 3;
  if (iVar2 != 0) {
    iVar1 = (uint)(iVar2 == 1) << 2;
  }
  if (iVar1 == 4) {
    lVar7 = *(long *)(lVar6 + 0x38);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (*(long *)(lVar7 + 0x10) != 0) {
      lVar7 = *(long *)(lVar6 + 0x38);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = *(long *)(lVar7 + 0x10);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = LitJson_JsonData__get_Item(lVar7,_str_success,0);
      uVar13 = LitJson_JsonData__op_Explicit(0,uVar5,0);
      if ((uVar13 & 1) == 0) {
        lVar7 = *(long *)(lVar6 + 0x38);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        lVar7 = *(long *)(lVar7 + 0x10);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        uVar5 = LitJson_JsonData__get_Item(lVar7,_str_errorType,0);
        LitJson_JsonData__op_Explicit(0,uVar5,0);
      }
      else {
        if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar7 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
        if (*(long *)(lVar6 + 0x20) == 0) {
          func_0x010eec64(0);
        }
        uVar4 = FinishScenario__get_m_nextScenarioId();
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar7 + 0xac) = uVar4;
        lVar7 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        SceneDataPipe__ClearInfo(lVar7,0,0);
        plVar10 = *(long **)(lVar6 + 0x20);
        uVar5 = *(undefined8 *)(lVar6 + 0x10);
        cVar3 = *(char *)(lVar6 + 0x18);
        if (plVar10 == (long *)0x0) {
          func_0x010eec64(0);
        }
        (**(code **)(*plVar10 + 0x460))
                  (plVar10,uVar5,cVar3 != '\0',*(undefined8 *)(*plVar10 + 0x468));
      }
    }
    uVar5 = 0;
    *(undefined4 *)(lVar6 + 0x34) = 0xffffffff;
  }
  else {
    uVar5 = 0;
    if (iVar1 == 3) {
      lVar7 = func_0x010fdda8(
                             _class_FinishScenario_<SendSetRecentScenario>c__Iterator0_<SendSetRecentScenario>c__AnonStorey4_TypeInfo
                             );
      System_Object___ctor(lVar7,0);
      *(long *)(lVar6 + 0x38) = lVar7;
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      *(long *)(lVar7 + 0x18) = lVar6;
      lVar7 = *(long *)(lVar6 + 0x38);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      *(undefined8 *)(lVar7 + 0x10) = 0;
      lVar7 = *(long *)(lVar6 + 0x20);
      if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar8 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
      uVar5 = 
      _mm_Method_FinishScenario_<SendSetRecentScenario>c__Iterator0_<SendSetRecentScenario>c__AnonStorey4_<>m__0__
      ;
      uVar14 = *(undefined8 *)(lVar6 + 0x38);
      uVar9 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
      HttpRequest_ResponseCallback___ctor(uVar9,uVar14,uVar5,0);
      plVar10 = (long *)func_0x01101ad4(_class_object___TypeInfo,2);
      if (plVar10 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((_str_scenarioId != 0) &&
         (lVar11 = func_0x010fdfcc(_str_scenarioId,*(undefined8 *)(*plVar10 + 0x40)), lVar11 == 0))
      {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      lVar11 = _str_scenarioId;
      if ((int)plVar10[3] == 0) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[4] = lVar11;
      if (*(long *)(lVar6 + 0x20) == 0) {
        func_0x010eec64(0);
      }
      uStack_64 = FinishScenario__get_m_nextScenarioId();
      lVar11 = func_0x010fdd08(_class_int_TypeInfo,&uStack_64);
      if ((lVar11 != 0) &&
         (lVar12 = func_0x010fdfcc(lVar11,*(undefined8 *)(*plVar10 + 0x40)), lVar12 == 0)) {
        uVar5 = func_0x010efe90();
        func_0x010eebec(uVar5,0,0);
      }
      if (*(uint *)(plVar10 + 3) < 2) {
        uVar5 = func_0x010ef850();
        func_0x010eebec(uVar5,0,0);
      }
      plVar10[5] = lVar11;
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = HttpRequest__SendAndWait
                        (lVar8,_str__scenario_SetRecentScenario,_str_POST,1,uVar9,plVar10,0);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = UnityEngine_MonoBehaviour__StartCoroutine(lVar7,uVar5,0);
      *(undefined8 *)(lVar6 + 0x28) = uVar5;
      uVar5 = 1;
      if (*(char *)(lVar6 + 0x30) == '\0') {
        *(undefined4 *)(lVar6 + 0x34) = 1;
      }
    }
  }
  return uVar5;
}


