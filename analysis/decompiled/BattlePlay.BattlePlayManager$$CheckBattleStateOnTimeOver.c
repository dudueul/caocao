// name: BattlePlay.BattlePlayManager$$CheckBattleStateOnTimeOver
// entry: 0197aa5c

/* WARNING: Removing unreachable block (ram,0x01895e24) */
/* WARNING: Removing unreachable block (ram,0x01895e2c) */
/* WARNING: Removing unreachable block (ram,0x01895e34) */
/* WARNING: Removing unreachable block (ram,0x0215b9bc) */
/* WARNING: Removing unreachable block (ram,0x0215b9d4) */
/* WARNING: Removing unreachable block (ram,0x0215b9e8) */
/* WARNING: Removing unreachable block (ram,0x0215b9f0) */
/* WARNING: Removing unreachable block (ram,0x0215b9f4) */
/* WARNING: Removing unreachable block (ram,0x0215ba08) */
/* WARNING: Removing unreachable block (ram,0x0215ba0c) */
/* WARNING: Removing unreachable block (ram,0x0215ba10) */
/* WARNING: Removing unreachable block (ram,0x0215ba1c) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__CheckBattleStateOnTimeOver(long param_1)

{
  int iVar1;
  uint uVar2;
  int iVar3;
  long *plVar4;
  long *plVar5;
  undefined8 uVar6;
  long lVar7;
  long lVar8;
  long lVar9;
  ulong uVar10;
  undefined8 uVar11;
  code *pcVar12;
  ushort uVar13;
  undefined8 *puVar14;
  long lVar15;
  undefined8 uVar16;
  float fVar17;
  undefined4 uVar18;
  float fVar19;
  
  if ((bRam00000000049fae16 & 1) == 0) {
    func_0x010c65d4(0x205e);
    bRam00000000049fae16 = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar16 = *(undefined8 *)(lVar9 + 0x128);
  uVar18 = *(undefined4 *)(lVar9 + 0x130);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar16,uVar18,0);
  if ((uVar2 & 1) == 0) {
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = ControlFlow__get_remainedSeconds(lVar9,0);
    if (0 < iVar3) {
code_r0x0197abf8:
      uVar18 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
      *(undefined4 *)(param_1 + 0x40) = uVar18;
      return;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = ControlFlow__get_controlStatus(lVar9,0);
    if (iVar3 == 0xf) goto code_r0x0197abf8;
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = ControlFlow__get_controlStatus(lVar9,0);
    if (iVar3 == 1) goto code_r0x0197abf8;
  }
  fVar19 = (float)UnityEngine_Time__get_realtimeSinceStartup(0,0);
  if (fVar19 - *(float *)(param_1 + 0x40) < 5.0) {
    return;
  }
  uVar18 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
  *(undefined4 *)(param_1 + 0x40) = uVar18;
  if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = CaocaoLoadIndicator__get_isLoading(lVar9,0);
  if ((uVar10 & 1) != 0) {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar10 = Multiplay_MultiplayManager__get_isWatchingBattle(0,0);
    if ((uVar10 & 1) != 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      *(undefined1 *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x11) = 0;
      if (((*(byte *)(_class_SceneSingleton<TimeController>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<TimeController>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                        (0,_mm_Method_SceneSingleton<TimeController>_get_instance__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      TimeController__SetSpeed(lVar9,3,0);
      if (*(char *)(*(long *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xb8) + 0x20) != '\0') {
        if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar9 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        Multiplay_MultiplayManager__OnWatchFinishedPostActionOnLoading(lVar9,0);
      }
    }
    if (((*(byte *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<CaocaoLoadIndicator>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar9 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<CaocaoLoadIndicator>_get_instance__);
    uVar16 = _mm_Method_BattlePlay_BattlePlayManager_<CheckBattleStateOnTimeOver>m__0__;
    uVar11 = func_0x010fdda8(_class_System_Action_TypeInfo);
    System_Action___ctor(uVar11,param_1,uVar16,0);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    CaocaoLoadIndicator__DestroyLoadingUI(lVar9,uVar11,0);
    if ((bRam00000000049fae1a & 1) == 0) {
      func_0x010c65d4(0x206f);
      bRam00000000049fae1a = 1;
    }
    *(undefined4 *)(param_1 + 0x48) = 0x3ea3d70a;
    if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar10 = LoadingUI__IsExistInstance(0,0);
    if ((uVar10 & 1) == 0) {
      return;
    }
    if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    fVar19 = *(float *)(param_1 + 0x48);
    lVar9 = **(long **)(_class_LoadingUI_TypeInfo + 0xb8);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    fVar19 = fVar19 + 0.68;
    if ((bRam00000000049fa4f1 & 1) == 0) {
      func_0x010c65d4(0x84a2,0,0);
      bRam00000000049fa4f1 = 1;
    }
    if (fVar19 == 0.0) {
      lVar15 = *(long *)(lVar9 + 0x18);
      if (lVar15 == 0) {
        func_0x010eec64(0);
      }
      UIBasicSprite__set_fillAmount(0,lVar15,0);
      lVar9 = *(long *)(lVar9 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar16 = _str_0_;
      if ((bRam00000000049ff6b6 & 1) == 0) {
        func_0x010c65d4(0xe6fe,_str_0_,0);
        bRam00000000049ff6b6 = 1;
      }
      lVar15 = *(long *)(lVar9 + 0x278);
      *(undefined1 *)(lVar9 + 0x29a) = 0;
      if (lVar15 == 0) {
        func_0x010eec64(0);
      }
      System_Collections_Generic_List<tk2dUICamera>__Clear
                (lVar15,
                 _mm_Method_System_Collections_Generic_List<UILabelLocalized_AppendCtx>_Clear__);
      *(undefined1 *)(lVar9 + 0x288) = 1;
      *(undefined8 *)(lVar9 + 0x268) = uVar16;
      *(undefined8 *)(lVar9 + 0x270) = 0;
      *(undefined1 *)(lVar9 + 0x299) = 0;
      return;
    }
    lVar15 = *(long *)(lVar9 + 0x38);
    lVar9 = *(long *)(lVar9 + 0x18);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    fVar17 = (float)UIBasicSprite__get_fillAmount(lVar9,0);
    if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    iVar3 = UnityEngine_Mathf__CeilToInt(fVar17 * 100.0,0,0);
    iVar1 = UnityEngine_Mathf__CeilToInt(fVar19 * 100.0,0,0);
    if (lVar15 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam00000000049fd7cf & 1) == 0) {
      func_0x010c65d4(0x5db8);
      bRam00000000049fd7cf = 1;
    }
    *(undefined8 *)(lVar15 + 0x38) = 0;
    *(int *)(lVar15 + 0x40) = iVar1;
    *(long *)(lVar15 + 0x20) = lVar9;
    *(float *)(lVar15 + 0x28) = (float)iVar3 / 100.0;
    *(float *)(lVar15 + 0x2c) = (float)iVar1 / 100.0;
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar2 = UnityEngine_Object__op_Equality(0,lVar9,0,0);
    if ((uVar2 & 1) == 0) {
      uVar16 = UnityEngine_Component__get_gameObject(lVar15,0);
      uVar11 = *(undefined8 *)(lVar15 + 0x18);
      if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class__iTween_TypeInfo);
      }
      _iTween__StopByName(0,uVar16,uVar11,0,0);
      if (((*(byte *)(_class_itDic_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_itDic_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = itDic__Allocate(0,0);
      uVar16 = *(undefined8 *)(lVar15 + 0x18);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<itDic>(lVar9,_str_name,uVar16,_mm_Method_itDic_Set<string>__);
      uVar18 = *(undefined4 *)(lVar15 + 0x28);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<float>(uVar18,lVar9,_str_from,_mm_Method_itDic_Set<float>__);
      uVar18 = *(undefined4 *)(lVar15 + 0x2c);
      if (lVar9 == 0) {
        func_0x010eec64(0);
        itDic__Set<float>(uVar18,0,_str_to,_mm_Method_itDic_Set<float>__);
        func_0x010eec64(0);
        itDic__Set<float>(0x3e800000,0,_str_time,_mm_Method_itDic_Set<float>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<float>(uVar18,lVar9,_str_to,_mm_Method_itDic_Set<float>__);
        itDic__Set<float>(0x3e800000,lVar9,_str_time,_mm_Method_itDic_Set<float>__);
      }
      itDic__Set<itDic>(lVar9,_str_easetype,_str_easeInSine,_mm_Method_itDic_Set<string>__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<itDic>(lVar9,_str_onupdate,_str_OnGuageChangeUpdated,_mm_Method_itDic_Set<string>__
                       );
      uVar16 = UnityEngine_Component__get_gameObject(lVar15,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_onupdatetarget,uVar16,_mm_Method_itDic_Set<GameObject>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar9,_str_onupdatetarget,uVar16,_mm_Method_itDic_Set<GameObject>__);
      }
      itDic__Set<bool>(lVar9,_str_ignoretimescale,1,_mm_Method_itDic_Set<bool>__);
      uVar16 = UnityEngine_Component__get_gameObject(lVar15,0);
      _iTween__ValueTo(0,uVar16,lVar9,0);
      uVar16 = *(undefined8 *)(lVar15 + 0x38);
      if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = UnityEngine_Object__op_Inequality(0,uVar16,0,0);
      if ((uVar10 & 1) == 0) {
        return;
      }
      if (((*(byte *)(_class_itDic_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_itDic_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar9 = itDic__Allocate(0,0);
      uVar16 = *(undefined8 *)(lVar15 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_name,uVar16,_mm_Method_itDic_Set<string>__);
        func_0x010eec64(0);
        itDic__Set<int>(0,_str_from,iVar3,_mm_Method_itDic_Set<int>__);
        func_0x010eec64(0);
        itDic__Set<int>(0,_str_to,iVar1,_mm_Method_itDic_Set<int>__);
        func_0x010eec64(0);
        itDic__Set<float>(0x3e800000,0,_str_time,_mm_Method_itDic_Set<float>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar9,_str_name,uVar16,_mm_Method_itDic_Set<string>__);
        itDic__Set<int>(lVar9,_str_from,iVar3,_mm_Method_itDic_Set<int>__);
        itDic__Set<int>(lVar9,_str_to,iVar1,_mm_Method_itDic_Set<int>__);
        itDic__Set<float>(0x3e800000,lVar9,_str_time,_mm_Method_itDic_Set<float>__);
      }
      itDic__Set<itDic>(lVar9,_str_easetype,_str_easeOutQuart,_mm_Method_itDic_Set<string>__);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<itDic>(lVar9,_str_onupdate,_str_OnLabelChangeUpdated,_mm_Method_itDic_Set<string>__
                       );
      uVar16 = UnityEngine_Component__get_gameObject(lVar15,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_onupdatetarget,uVar16,_mm_Method_itDic_Set<GameObject>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar9,_str_onupdatetarget,uVar16,_mm_Method_itDic_Set<GameObject>__);
      }
      itDic__Set<itDic>(lVar9,_str_oncomplete,_str_OnLabelAnimCompleted,
                        _mm_Method_itDic_Set<string>__);
      uVar16 = UnityEngine_Component__get_gameObject(lVar15,0);
      if (lVar9 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_oncompletetarget,uVar16,_mm_Method_itDic_Set<GameObject>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar9,_str_oncompletetarget,uVar16,_mm_Method_itDic_Set<GameObject>__);
      }
      itDic__Set<bool>(lVar9,_str_ignoretimescale,1,_mm_Method_itDic_Set<bool>__);
      lVar15 = UnityEngine_Component__get_gameObject(lVar15,0);
      if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class__iTween_TypeInfo);
      }
      if ((bRam00000000049fa218 & 1) == 0) {
        func_0x010c65d4(0x10e05,lVar15,lVar9,0);
        bRam00000000049fa218 = 1;
      }
      if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      if (lVar9 == 0) {
        func_0x010eec64(0);
        itDic__CleanArgs(0,0);
        func_0x010eec64(0);
      }
      else {
        itDic__CleanArgs(lVar9,0);
      }
      lVar7 = itDic__get_callbacks(lVar9,0);
      if (lVar7 == 0) {
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        uVar10 = itDic__Contains(lVar9,_str_onupdate,0);
        if ((uVar10 & 1) == 0) {
          return;
        }
      }
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      uVar10 = itDic__Contains(lVar9,_str_from,0);
      if ((uVar10 & 1) != 0) {
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        uVar10 = itDic__Contains(lVar9,_str_to,0);
        if ((uVar10 & 1) != 0) {
          if (lVar9 == 0) {
            func_0x010eec64(0);
            itDic__Set<itDic>(0,_str_type,_str_value,_mm_Method_itDic_Set<string>__);
            func_0x010eec64(0);
          }
          else {
            itDic__Set<itDic>(lVar9,_str_type,_str_value,_mm_Method_itDic_Set<string>__);
          }
          lVar7 = itDic__GetType(lVar9,_str_from,0);
          uVar16 = _class_UnityEngine_Vector2_var;
          if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_System_Type_TypeInfo);
          }
          lVar8 = System_Type__GetTypeFromHandle(0,uVar16,0);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          if (lVar7 == lVar8) {
            puVar14 = (undefined8 *)&str_vector2;
          }
          else {
            lVar7 = itDic__GetType(lVar9,_str_from,0);
            uVar16 = _class_UnityEngine_Vector3_var;
            if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c(_class_System_Type_TypeInfo);
            }
            lVar8 = System_Type__GetTypeFromHandle(0,uVar16,0);
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            if (lVar7 == lVar8) {
              puVar14 = (undefined8 *)&str_vector3;
            }
            else {
              lVar7 = itDic__GetType(lVar9,_str_from,0);
              uVar16 = _class_UnityEngine_Rect_var;
              if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c(_class_System_Type_TypeInfo);
              }
              lVar8 = System_Type__GetTypeFromHandle(0,uVar16,0);
              if (lVar9 == 0) {
                func_0x010eec64(0);
              }
              if (lVar7 == lVar8) {
                puVar14 = (undefined8 *)&str_rect;
              }
              else {
                lVar7 = itDic__GetType(lVar9,_str_from,0);
                uVar16 = _class_float_var;
                if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                   (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                  func_0x010da55c(_class_System_Type_TypeInfo);
                }
                lVar8 = System_Type__GetTypeFromHandle(0,uVar16,0);
                if (lVar9 == 0) {
                  func_0x010eec64(0);
                }
                if (lVar7 == lVar8) {
                  puVar14 = (undefined8 *)&str_float;
                }
                else {
                  lVar7 = itDic__GetType(lVar9,_str_from,0);
                  uVar16 = _class_UnityEngine_Color_var;
                  if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                     (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                    func_0x010da55c(_class_System_Type_TypeInfo);
                  }
                  lVar8 = System_Type__GetTypeFromHandle(0,uVar16,0);
                  if (lVar7 != lVar8) {
                    return;
                  }
                  if (lVar9 == 0) {
                    func_0x010eec64(0);
                  }
                  puVar14 = (undefined8 *)&str_color;
                }
              }
            }
          }
          itDic__Set<itDic>(lVar9,_str_method,*puVar14,_mm_Method_itDic_Set<string>__);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          uVar2 = itDic__Contains(lVar9,_str_easetype,0);
          if ((uVar2 & 1) == 0) {
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            itDic__Set<iTween_EaseType>
                      (lVar9,_str_easetype,0x15,_mm_Method_itDic_Set<iTween_EaseType>__);
          }
          if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          if ((bRam00000000049fa2e0 & 1) == 0) {
            func_0x010c65d4(0x10daa);
            bRam00000000049fa2e0 = 1;
          }
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          uVar2 = itDic__Contains(lVar9,_str_id,0);
          if ((uVar2 & 1) == 0) {
            if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar16 = _iTween__GenerateID();
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            itDic__Set<itDic>(lVar9,_str_id,uVar16,_mm_Method_itDic_Set<string>__);
          }
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          uVar10 = itDic__Contains(lVar9,_str_target,0);
          if ((uVar10 & 1) == 0) {
            if (lVar9 == 0) {
              func_0x010eec64(0);
            }
            itDic__Set<itDic>(lVar9,_str_target,lVar15,_mm_Method_itDic_Set<GameObject>__);
          }
          lVar7 = func_0x010fdda8(_class__iTween_TypeInfo);
          *(undefined1 *)(lVar7 + 0x18) = 1;
          *(undefined8 *)(lVar7 + 0x1c) = 0x1ffffffff;
          System_Object___ctor(lVar7,0);
          *(long *)(lVar7 + 0x80) = lVar9;
          uVar16 = func_0x010fdda8(_class_WeakReference<GameObject>_TypeInfo);
          WeakReference<GameObject>___ctor
                    (uVar16,lVar15,_mm_Method_WeakReference<GameObject>__ctor__);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          *(undefined8 *)(lVar7 + 0x10) = uVar16;
          if (lVar15 == 0) {
            func_0x010eec64(0);
            uVar18 = UnityEngine_Object__GetInstanceID(0,0);
            *(undefined4 *)(lVar7 + 0x1c) = uVar18;
            func_0x010eec64(0);
          }
          else {
            uVar18 = UnityEngine_Object__GetInstanceID(lVar15,0);
            *(undefined4 *)(lVar7 + 0x1c) = uVar18;
          }
          uVar16 = UnityEngine_GameObject__get_transform(lVar15,0);
          lVar15 = *(long *)(lVar7 + 0x10);
          *(undefined8 *)(lVar7 + 0x100) = uVar16;
          if (lVar15 == 0) {
            func_0x010eec64(0);
          }
          lVar15 = WeakReference<Renderer>__get_target
                             (lVar15,_mm_Method_WeakReference<GameObject>_get_target__);
          if (lVar15 == 0) {
            func_0x010eec64(0);
          }
          uVar2 = UnityEngine_GameObject__get_activeInHierarchy(lVar15,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
            uRam0000000000000020 = uVar2 & 1;
            func_0x010eec64(0);
            lRam0000000000000080 = lVar9;
            func_0x010eec64(0);
            _iTween__RetrieveArgs(0);
            func_0x010eec64(0);
          }
          else {
            *(uint *)(lVar7 + 0x20) = uVar2 & 1;
            *(long *)(lVar7 + 0x80) = lVar9;
            _iTween__RetrieveArgs(lVar7);
          }
          *(undefined4 *)(lVar7 + 0x24) = *(undefined4 *)(lVar7 + 0x50);
          uVar18 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          *(undefined4 *)(lVar7 + 0xf4) = uVar18;
          if (((*(byte *)(_class_Singleton<iTweenManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<iTweenManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar9 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<iTweenManager>_get_instance__);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          if ((bRam00000000049ffef6 & 1) == 0) {
            func_0x010c65d4(0x10e8d,lVar7,0);
            bRam00000000049ffef6 = 1;
          }
          lVar15 = *(long *)(lVar9 + 0x28);
          if (lVar15 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_LinkedList<object>__AddLast
                    (lVar15,lVar7,
                     _mm_Method_System_Collections_Generic_LinkedList<_iTween>_AddLast__);
          if ((bRam00000000049ffef7 & 1) == 0) {
            func_0x010c65d4(0x10e8c);
            bRam00000000049ffef7 = 1;
          }
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          uVar18 = *(undefined4 *)(lVar7 + 0x1c);
          lVar15 = *(long *)(lVar9 + 0x20);
          if (lVar15 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_Dictionary<int,_UserUnitRelation>__TryGetValue
                    (lVar15,uVar18,&stack0xffffffffffffffc8,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_List<_iTween>>_TryGetValue__
                    );
          lVar15 = *(long *)(lVar9 + 0x20);
          lVar9 = func_0x010fdda8(_class_System_Collections_Generic_List<_iTween>_TypeInfo);
          System_Collections_Generic_List<tk2dUILayoutItem>___ctor
                    (lVar9,_mm_Method_System_Collections_Generic_List<_iTween>__ctor__);
          if (lVar15 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_Dictionary<int,_tk2dFontChar>__set_Item
                    (lVar15,uVar18,lVar9,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_List<_iTween>>_set_Item__
                    );
          if (lVar9 == 0) {
            func_0x010eec64(0);
            lVar9 = 0;
          }
          System_Collections_Generic_List<tk2dUILayoutItem>__Add
                    (lVar9,lVar7,_mm_Method_System_Collections_Generic_List<_iTween>_Add__);
          return;
        }
      }
      return;
    }
    return;
  }
  if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049f7afd & 1) == 0) {
    func_0x010c65d4(0x8a27,0,0);
    bRam00000000049f7afd = 1;
  }
  if (*(char *)(lVar9 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = Multiplay_MultiplayManager__get_isMyActiveBattle();
  if ((uVar10 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar15 = SceneSingleton<WorldBossBattleController>__get_instance
                     (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar15 == 0) {
    func_0x010eec64(0);
  }
  uVar16 = *(undefined8 *)(lVar15 + 0x128);
  uVar18 = *(undefined4 *)(lVar15 + 0x130);
  if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) != 0)
     && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar16,uVar18,0);
  if ((uVar2 & 1) != 0) {
    return;
  }
  if ((*(long *)(lVar9 + 0x40) != 0) && (*(long *)(lVar9 + 0x48) != 0)) {
    uVar16 = BattlePlay_BattlePlayData__get_battleKey(*(long *)(lVar9 + 0x48),0);
    uVar11 = *(undefined8 *)(lVar9 + 0x48);
    plVar4 = (long *)func_0x010fdda8(_class_BattlePlay_BattleState_TypeInfo);
    BattlePlay_BattleState___ctor(plVar4,uVar16,uVar11,0);
    if (plVar4 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((plVar4[6] != 0) &&
       (iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                          (plVar4[6],
                           _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Count__),
       iVar3 != 0)) {
      if ((*(ushort *)(lVar9 + 100) & 0xff) == 0) {
        uVar13 = *(ushort *)(lVar9 + 100) >> 8;
      }
      else {
        iVar3 = -1;
        if ((int)plVar4[7] < 1) {
          iVar3 = 1;
        }
        *(int *)(plVar4 + 7) = (int)plVar4[7] + iVar3;
        *(undefined1 *)(lVar9 + 100) = 0;
        uVar13 = (ushort)*(byte *)(lVar9 + 0x65);
      }
      if (uVar13 != 0) {
        lVar15 = plVar4[6];
        if (lVar15 == 0) {
          func_0x010eec64(0);
        }
        iVar3 = System_Collections_Generic_List<tk2dUILayoutItem>__get_Count
                          (lVar15,
                           _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Count__);
        if (0 < iVar3) {
          iVar1 = 0;
          do {
            if (plVar4 == (long *)0x0) {
              func_0x010eec64(0);
            }
            lVar15 = plVar4[6];
            if (lVar15 == 0) {
              func_0x010eec64(0);
            }
            lVar15 = System_Collections_Generic_List<object>__get_Item
                               (lVar15,iVar1,
                                _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Item__
                               );
            if (lVar15 == 0) {
              func_0x010eec64(0);
            }
            if (0 < *(int *)(lVar15 + 0x24)) {
              if (plVar4 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar15 = plVar4[6];
              if (lVar15 == 0) {
                func_0x010eec64(0);
              }
              lVar15 = System_Collections_Generic_List<object>__get_Item
                                 (lVar15,iVar1,
                                  _mm_Method_System_Collections_Generic_List<BattleUnitState>_get_Item__
                                 );
              if (lVar15 == 0) {
                func_0x010eec64(0);
                iVar3 = iRam0000000000000024;
                func_0x010eec64(0);
              }
              else {
                iVar3 = *(int *)(lVar15 + 0x24);
              }
              *(int *)(lVar15 + 0x24) = iVar3 + 10;
              break;
            }
            iVar1 = iVar1 + 1;
          } while (iVar1 < iVar3);
        }
        *(undefined1 *)(lVar9 + 0x65) = 0;
      }
      plVar5 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
      LitJson_JsonWriter___ctor(plVar5,0);
      if (plVar4 == (long *)0x0) {
        func_0x010eec64(0);
      }
      (**(code **)(*plVar4 + 0x170))(plVar4,plVar5,*(undefined8 *)(*plVar4 + 0x178));
      lVar15 = *(long *)(lVar9 + 0x48);
      if (lVar15 == 0) {
        func_0x010eec64(0);
      }
      uVar16 = BattlePlay_BattlePlayData__get_battleKey(lVar15,0);
      if (plVar5 == (long *)0x0) {
        func_0x010eec64(0);
      }
      uVar11 = (**(code **)(*plVar5 + 0x160))(plVar5,*(undefined8 *)(*plVar5 + 0x168));
      plVar4 = *(long **)(lVar9 + 0x40);
      if (plVar4 == (long *)0x0) {
        uVar18 = 8;
      }
      else {
        uVar18 = (**(code **)(*plVar4 + 0x170))(plVar4,*(undefined8 *)(*plVar4 + 0x178));
      }
      uVar6 = func_0x010fdda8(_class_<>__AnonType48<string,_string,_bool,_Step>_TypeInfo);
      <>__AnonType48<string,_string,_bool,_Step>___ctor
                (uVar6,uVar16,uVar11,0,uVar18,
                 _mm_Method_<>__AnonType48<string,_string,_bool,_Step>__ctor__);
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LitJson_JsonMapper__ToJson(0,uVar6,0);
      uVar16 = _str_checkBattleState;
      goto Multiplay_MultiplayManager__EmitSocketMessage;
    }
  }
  if ((bRam00000000049f7ad5 & 1) == 0) {
    func_0x010c65d4(0x8a8a);
    bRam00000000049f7ad5 = 1;
  }
  if (*(char *)(lVar9 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar2 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar2 & 1) != 0) {
    return;
  }
  plVar4 = *(long **)(lVar9 + 0x40);
  if (plVar4 == (long *)0x0) {
code_r0x013a22a0:
    uVar18 = 8;
code_r0x013a22a4:
    uVar16 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
    <>__AnonType19<Step,_bool,_bool>___ctor
              (uVar16,uVar18,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
  }
  else {
    iVar3 = (**(code **)(*plVar4 + 0x170))(plVar4,*(undefined8 *)(*plVar4 + 0x178));
    if (iVar3 < 1) {
code_r0x013a2284:
      plVar4 = *(long **)(lVar9 + 0x40);
      if (plVar4 == (long *)0x0) goto code_r0x013a22a0;
      uVar18 = (**(code **)(*plVar4 + 0x170))(plVar4,*(undefined8 *)(*plVar4 + 0x178));
      goto code_r0x013a22a4;
    }
    if (*(long *)(lVar9 + 0x40) == 0) goto code_r0x013a22a0;
    if ((*(long *)(lVar9 + 0x48) == 0) ||
       (lVar15 = BattlePlay_BattlePlayData__get_opponent(*(long *)(lVar9 + 0x48),0), lVar15 == 0))
    goto code_r0x013a2284;
    lVar15 = *(long *)(lVar9 + 0x48);
    if (lVar15 == 0) {
      func_0x010eec64(0);
    }
    uVar11 = BattlePlay_BattlePlayData__get_battleKey(lVar15,0);
    plVar4 = *(long **)(lVar9 + 0x40);
    if (plVar4 == (long *)0x0) {
      uVar18 = 8;
    }
    else {
      uVar18 = (**(code **)(*plVar4 + 0x170))(plVar4,*(undefined8 *)(*plVar4 + 0x178));
    }
    uVar16 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
    <>__AnonType18<string,_bool,_bool,_Step>___ctor
              (uVar16,uVar11,1,0,uVar18,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__)
    ;
  }
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar11 = LitJson_JsonMapper__ToJson(0,uVar16,0);
  uVar16 = _str_cancelMatchmaking;
Multiplay_MultiplayManager__EmitSocketMessage:
  uVar10 = MyUserData__get_hasInstance(0,0);
  if ((uVar10 & 1) != 0) {
    lVar15 = MyUserData__get_instance(0,0);
    if (lVar15 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar15 + 0x268) != '\0') {
      lVar9 = *(long *)(lVar9 + 0x30);
      if (lVar9 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar16,uVar11,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar16 = System_String__Format(0,_str____0____1__,uVar16,uVar11,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = LitJson_JsonMapper__ToObject(0,uVar16,0);
      lVar7 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar16 = _str__;
      System_Object___ctor(lVar7,0);
      *(undefined4 *)(lVar7 + 0x14) = 2;
      *(undefined4 *)(lVar7 + 0x18) = 0;
      *(undefined8 *)(lVar7 + 0x30) = uVar11;
      *(undefined8 *)(lVar7 + 0x20) = uVar16;
      *(undefined4 *)(lVar7 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar7 + 0x10) = 4;
      lVar15 = lVar9;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar15 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar8 = *(long *)(lVar9 + 0x48);
      if (*(long *)(lVar9 + 0x50) == 0) {
        lVar15 = func_0x010eec64(0);
      }
      uVar16 = SocketIO_Encoder__Encode(lVar15,lVar7);
      if (lVar8 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar8,uVar16,0);
      return;
    }
  }
  uVar10 = Multiplay_MultiplayManager__get_isConnected(lVar9);
  if ((uVar10 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(lVar9 + 0x70) = 0;
  *(undefined1 *)(lVar9 + 0x141) = 0;
  *(undefined1 *)(lVar9 + 0x142) = 0;
  uVar16 = *(undefined8 *)(lVar9 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = UnityEngine_Object__op_Inequality(0,uVar16,0,0);
  if ((uVar10 & 1) == 0) {
    return;
  }
  lVar15 = *(long *)(lVar9 + 0x30);
  if (lVar15 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Close(lVar15,0);
  uVar16 = *(undefined8 *)(lVar9 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam0000000004a04120 & 1) == 0) {
    func_0x010c65d4(0x906e,uVar16,0);
    bRam0000000004a04120 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  pcVar12 = pcRam0000000004a04118;
  if ((pcRam0000000004a04118 == (code *)0x0) &&
     (pcVar12 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar12 == (code *)0x0)) {
    uVar11 = func_0x010f000c(&UNK_039a20c7);
    func_0x010eebec(uVar11,0,0);
  }
  pcRam0000000004a04118 = pcVar12;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04118)(uVar16,0);
  return;
}


