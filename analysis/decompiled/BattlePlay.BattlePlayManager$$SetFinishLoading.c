// name: BattlePlay.BattlePlayManager$$SetFinishLoading
// entry: 0197a250

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__SetFinishLoading(long param_1,uint param_2)

{
  int iVar1;
  int iVar2;
  uint uVar3;
  long lVar4;
  long lVar5;
  ulong uVar6;
  undefined8 uVar7;
  undefined8 *puVar8;
  long lVar9;
  long lVar10;
  undefined8 uVar11;
  float fVar12;
  float fVar13;
  undefined4 uVar14;
  
  if ((bRam00000000049fae1a & 1) == 0) {
    func_0x010c65d4(0x206f);
    bRam00000000049fae1a = 1;
  }
  *(undefined4 *)(param_1 + 0x48) = 0x3ea3d70a;
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = LoadingUI__IsExistInstance(0,0);
  if ((uVar6 & 1) == 0) {
    return;
  }
  if (((*(byte *)(_class_LoadingUI_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LoadingUI_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  fVar13 = *(float *)(param_1 + 0x48);
  lVar10 = **(long **)(_class_LoadingUI_TypeInfo + 0xb8);
  if (lVar10 == 0) {
    func_0x010eec64(0);
  }
  fVar13 = fVar13 + 0.68;
  if ((bRam00000000049fa4f1 & 1) == 0) {
    func_0x010c65d4(0x84a2,param_2 & 1,0);
    bRam00000000049fa4f1 = 1;
  }
  if (fVar13 == 0.0) {
    lVar9 = *(long *)(lVar10 + 0x18);
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    UIBasicSprite__set_fillAmount(0,lVar9,0);
    lVar10 = *(long *)(lVar10 + 0x30);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    uVar7 = _str_0_;
    if ((bRam00000000049ff6b6 & 1) == 0) {
      func_0x010c65d4(0xe6fe,_str_0_,0);
      bRam00000000049ff6b6 = 1;
    }
    lVar9 = *(long *)(lVar10 + 0x278);
    *(undefined1 *)(lVar10 + 0x29a) = 0;
    if (lVar9 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_List<tk2dUICamera>__Clear
              (lVar9,_mm_Method_System_Collections_Generic_List<UILabelLocalized_AppendCtx>_Clear__)
    ;
    *(undefined1 *)(lVar10 + 0x288) = 1;
    *(undefined8 *)(lVar10 + 0x268) = uVar7;
    *(undefined8 *)(lVar10 + 0x270) = 0;
    *(undefined1 *)(lVar10 + 0x299) = 0;
    return;
  }
  if ((param_2 & 1) != 0) {
    lVar10 = *(long *)(lVar10 + 0x18);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam00000000049ff491 & 1) == 0) {
      func_0x010c65d4(0xe465,0);
      bRam00000000049ff491 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    fVar13 = (float)UnityEngine_Mathf__Clamp01(fVar13,0,0);
    if (*(float *)(lVar10 + 0x188) != fVar13) {
      *(float *)(lVar10 + 0x188) = fVar13;
      *(undefined1 *)(lVar10 + 0x58) = 1;
    }
    return;
  }
  lVar9 = *(long *)(lVar10 + 0x38);
  lVar10 = *(long *)(lVar10 + 0x18);
  if (lVar10 == 0) {
    func_0x010eec64(0);
  }
  fVar12 = (float)UIBasicSprite__get_fillAmount(lVar10,0);
  if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  iVar1 = UnityEngine_Mathf__CeilToInt(fVar12 * 100.0,0,0);
  iVar2 = UnityEngine_Mathf__CeilToInt(fVar13 * 100.0,0,0);
  if (lVar9 == 0) {
    func_0x010eec64(0);
  }
  if ((bRam00000000049fd7cf & 1) == 0) {
    func_0x010c65d4(0x5db8);
    bRam00000000049fd7cf = 1;
  }
  *(undefined8 *)(lVar9 + 0x38) = 0;
  *(int *)(lVar9 + 0x40) = iVar2;
  *(long *)(lVar9 + 0x20) = lVar10;
  *(float *)(lVar9 + 0x28) = (float)iVar1 / 100.0;
  *(float *)(lVar9 + 0x2c) = (float)iVar2 / 100.0;
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = UnityEngine_Object__op_Equality(0,lVar10,0,0);
  if ((uVar3 & 1) == 0) {
    uVar7 = UnityEngine_Component__get_gameObject(lVar9,0);
    uVar11 = *(undefined8 *)(lVar9 + 0x18);
    if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class__iTween_TypeInfo);
    }
    _iTween__StopByName(0,uVar7,uVar11,0,0);
    if (((*(byte *)(_class_itDic_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_itDic_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar10 = itDic__Allocate(0,0);
    uVar7 = *(undefined8 *)(lVar9 + 0x18);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    itDic__Set<itDic>(lVar10,_str_name,uVar7,_mm_Method_itDic_Set<string>__);
    uVar14 = *(undefined4 *)(lVar9 + 0x28);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    itDic__Set<float>(uVar14,lVar10,_str_from,_mm_Method_itDic_Set<float>__);
    uVar14 = *(undefined4 *)(lVar9 + 0x2c);
    if (lVar10 == 0) {
      func_0x010eec64(0);
      itDic__Set<float>(uVar14,0,_str_to,_mm_Method_itDic_Set<float>__);
      func_0x010eec64(0);
      itDic__Set<float>(0x3e800000,0,_str_time,_mm_Method_itDic_Set<float>__);
      func_0x010eec64(0);
    }
    else {
      itDic__Set<float>(uVar14,lVar10,_str_to,_mm_Method_itDic_Set<float>__);
      itDic__Set<float>(0x3e800000,lVar10,_str_time,_mm_Method_itDic_Set<float>__);
    }
    itDic__Set<itDic>(lVar10,_str_easetype,_str_easeInSine,_mm_Method_itDic_Set<string>__);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    itDic__Set<itDic>(lVar10,_str_onupdate,_str_OnGuageChangeUpdated,_mm_Method_itDic_Set<string>__)
    ;
    uVar7 = UnityEngine_Component__get_gameObject(lVar9,0);
    if (lVar10 == 0) {
      func_0x010eec64(0);
      itDic__Set<itDic>(0,_str_onupdatetarget,uVar7,_mm_Method_itDic_Set<GameObject>__);
      func_0x010eec64(0);
    }
    else {
      itDic__Set<itDic>(lVar10,_str_onupdatetarget,uVar7,_mm_Method_itDic_Set<GameObject>__);
    }
    itDic__Set<bool>(lVar10,_str_ignoretimescale,1,_mm_Method_itDic_Set<bool>__);
    uVar7 = UnityEngine_Component__get_gameObject(lVar9,0);
    _iTween__ValueTo(0,uVar7,lVar10,0);
    uVar7 = *(undefined8 *)(lVar9 + 0x38);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar6 = UnityEngine_Object__op_Inequality(0,uVar7,0,0);
    if ((uVar6 & 1) != 0) {
      if (((*(byte *)(_class_itDic_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_itDic_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = itDic__Allocate(0,0);
      uVar7 = *(undefined8 *)(lVar9 + 0x30);
      if (lVar10 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_name,uVar7,_mm_Method_itDic_Set<string>__);
        func_0x010eec64(0);
        itDic__Set<int>(0,_str_from,iVar1,_mm_Method_itDic_Set<int>__);
        func_0x010eec64(0);
        itDic__Set<int>(0,_str_to,iVar2,_mm_Method_itDic_Set<int>__);
        func_0x010eec64(0);
        itDic__Set<float>(0x3e800000,0,_str_time,_mm_Method_itDic_Set<float>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar10,_str_name,uVar7,_mm_Method_itDic_Set<string>__);
        itDic__Set<int>(lVar10,_str_from,iVar1,_mm_Method_itDic_Set<int>__);
        itDic__Set<int>(lVar10,_str_to,iVar2,_mm_Method_itDic_Set<int>__);
        itDic__Set<float>(0x3e800000,lVar10,_str_time,_mm_Method_itDic_Set<float>__);
      }
      itDic__Set<itDic>(lVar10,_str_easetype,_str_easeOutQuart,_mm_Method_itDic_Set<string>__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      itDic__Set<itDic>(lVar10,_str_onupdate,_str_OnLabelChangeUpdated,
                        _mm_Method_itDic_Set<string>__);
      uVar7 = UnityEngine_Component__get_gameObject(lVar9,0);
      if (lVar10 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_onupdatetarget,uVar7,_mm_Method_itDic_Set<GameObject>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar10,_str_onupdatetarget,uVar7,_mm_Method_itDic_Set<GameObject>__);
      }
      itDic__Set<itDic>(lVar10,_str_oncomplete,_str_OnLabelAnimCompleted,
                        _mm_Method_itDic_Set<string>__);
      uVar7 = UnityEngine_Component__get_gameObject(lVar9,0);
      if (lVar10 == 0) {
        func_0x010eec64(0);
        itDic__Set<itDic>(0,_str_oncompletetarget,uVar7,_mm_Method_itDic_Set<GameObject>__);
        func_0x010eec64(0);
      }
      else {
        itDic__Set<itDic>(lVar10,_str_oncompletetarget,uVar7,_mm_Method_itDic_Set<GameObject>__);
      }
      itDic__Set<bool>(lVar10,_str_ignoretimescale,1,_mm_Method_itDic_Set<bool>__);
      lVar9 = UnityEngine_Component__get_gameObject(lVar9,0);
      if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class__iTween_TypeInfo);
      }
      if ((bRam00000000049fa218 & 1) == 0) {
        func_0x010c65d4(0x10e05,lVar9,lVar10,0);
        bRam00000000049fa218 = 1;
      }
      if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      if (lVar10 == 0) {
        func_0x010eec64(0);
        itDic__CleanArgs(0,0);
        func_0x010eec64(0);
      }
      else {
        itDic__CleanArgs(lVar10,0);
      }
      lVar4 = itDic__get_callbacks(lVar10,0);
      if (lVar4 == 0) {
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        uVar6 = itDic__Contains(lVar10,_str_onupdate,0);
        if ((uVar6 & 1) == 0) {
          return;
        }
      }
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = itDic__Contains(lVar10,_str_from,0);
      if ((uVar6 & 1) != 0) {
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        uVar6 = itDic__Contains(lVar10,_str_to,0);
        if ((uVar6 & 1) != 0) {
          if (lVar10 == 0) {
            func_0x010eec64(0);
            itDic__Set<itDic>(0,_str_type,_str_value,_mm_Method_itDic_Set<string>__);
            func_0x010eec64(0);
          }
          else {
            itDic__Set<itDic>(lVar10,_str_type,_str_value,_mm_Method_itDic_Set<string>__);
          }
          lVar4 = itDic__GetType(lVar10,_str_from,0);
          uVar7 = _class_UnityEngine_Vector2_var;
          if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_System_Type_TypeInfo);
          }
          lVar5 = System_Type__GetTypeFromHandle(0,uVar7,0);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          if (lVar4 == lVar5) {
            puVar8 = (undefined8 *)&str_vector2;
          }
          else {
            lVar4 = itDic__GetType(lVar10,_str_from,0);
            uVar7 = _class_UnityEngine_Vector3_var;
            if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c(_class_System_Type_TypeInfo);
            }
            lVar5 = System_Type__GetTypeFromHandle(0,uVar7,0);
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            if (lVar4 == lVar5) {
              puVar8 = (undefined8 *)&str_vector3;
            }
            else {
              lVar4 = itDic__GetType(lVar10,_str_from,0);
              uVar7 = _class_UnityEngine_Rect_var;
              if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c(_class_System_Type_TypeInfo);
              }
              lVar5 = System_Type__GetTypeFromHandle(0,uVar7,0);
              if (lVar10 == 0) {
                func_0x010eec64(0);
              }
              if (lVar4 == lVar5) {
                puVar8 = (undefined8 *)&str_rect;
              }
              else {
                lVar4 = itDic__GetType(lVar10,_str_from,0);
                uVar7 = _class_float_var;
                if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                   (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                  func_0x010da55c(_class_System_Type_TypeInfo);
                }
                lVar5 = System_Type__GetTypeFromHandle(0,uVar7,0);
                if (lVar10 == 0) {
                  func_0x010eec64(0);
                }
                if (lVar4 == lVar5) {
                  puVar8 = (undefined8 *)&str_float;
                }
                else {
                  lVar4 = itDic__GetType(lVar10,_str_from,0);
                  uVar7 = _class_UnityEngine_Color_var;
                  if (((*(byte *)(_class_System_Type_TypeInfo + 0x12a) & 1) != 0) &&
                     (*(int *)(_class_System_Type_TypeInfo + 0xd8) == 0)) {
                    func_0x010da55c(_class_System_Type_TypeInfo);
                  }
                  lVar5 = System_Type__GetTypeFromHandle(0,uVar7,0);
                  if (lVar4 != lVar5) {
                    return;
                  }
                  if (lVar10 == 0) {
                    func_0x010eec64(0);
                  }
                  puVar8 = (undefined8 *)&str_color;
                }
              }
            }
          }
          itDic__Set<itDic>(lVar10,_str_method,*puVar8,_mm_Method_itDic_Set<string>__);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          uVar3 = itDic__Contains(lVar10,_str_easetype,0);
          if ((uVar3 & 1) == 0) {
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            itDic__Set<iTween_EaseType>
                      (lVar10,_str_easetype,0x15,_mm_Method_itDic_Set<iTween_EaseType>__);
          }
          if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          if ((bRam00000000049fa2e0 & 1) == 0) {
            func_0x010c65d4(0x10daa);
            bRam00000000049fa2e0 = 1;
          }
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          uVar3 = itDic__Contains(lVar10,_str_id,0);
          if ((uVar3 & 1) == 0) {
            if (((*(byte *)(_class__iTween_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class__iTween_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar7 = _iTween__GenerateID();
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            itDic__Set<itDic>(lVar10,_str_id,uVar7,_mm_Method_itDic_Set<string>__);
          }
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          uVar6 = itDic__Contains(lVar10,_str_target,0);
          if ((uVar6 & 1) == 0) {
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            itDic__Set<itDic>(lVar10,_str_target,lVar9,_mm_Method_itDic_Set<GameObject>__);
          }
          lVar4 = func_0x010fdda8(_class__iTween_TypeInfo);
          *(undefined1 *)(lVar4 + 0x18) = 1;
          *(undefined8 *)(lVar4 + 0x1c) = 0x1ffffffff;
          System_Object___ctor(lVar4,0);
          *(long *)(lVar4 + 0x80) = lVar10;
          uVar7 = func_0x010fdda8(_class_WeakReference<GameObject>_TypeInfo);
          WeakReference<GameObject>___ctor(uVar7,lVar9,_mm_Method_WeakReference<GameObject>__ctor__)
          ;
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          *(undefined8 *)(lVar4 + 0x10) = uVar7;
          if (lVar9 == 0) {
            func_0x010eec64(0);
            uVar14 = UnityEngine_Object__GetInstanceID(0,0);
            *(undefined4 *)(lVar4 + 0x1c) = uVar14;
            func_0x010eec64(0);
          }
          else {
            uVar14 = UnityEngine_Object__GetInstanceID(lVar9,0);
            *(undefined4 *)(lVar4 + 0x1c) = uVar14;
          }
          uVar7 = UnityEngine_GameObject__get_transform(lVar9,0);
          lVar9 = *(long *)(lVar4 + 0x10);
          *(undefined8 *)(lVar4 + 0x100) = uVar7;
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          lVar9 = WeakReference<Renderer>__get_target
                            (lVar9,_mm_Method_WeakReference<GameObject>_get_target__);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          uVar3 = UnityEngine_GameObject__get_activeInHierarchy(lVar9,0);
          if (lVar4 == 0) {
            func_0x010eec64(0);
            uRam0000000000000020 = uVar3 & 1;
            func_0x010eec64(0);
            lRam0000000000000080 = lVar10;
            func_0x010eec64(0);
            _iTween__RetrieveArgs(0);
            func_0x010eec64(0);
          }
          else {
            *(uint *)(lVar4 + 0x20) = uVar3 & 1;
            *(long *)(lVar4 + 0x80) = lVar10;
            _iTween__RetrieveArgs(lVar4);
          }
          *(undefined4 *)(lVar4 + 0x24) = *(undefined4 *)(lVar4 + 0x50);
          uVar14 = UnityEngine_Time__get_realtimeSinceStartup(0,0);
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          *(undefined4 *)(lVar4 + 0xf4) = uVar14;
          if (((*(byte *)(_class_Singleton<iTweenManager>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<iTweenManager>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar10 = Singleton<iTweenManager>__get_instance
                             (0,_mm_Method_Singleton<iTweenManager>_get_instance__);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          if ((bRam00000000049ffef6 & 1) == 0) {
            func_0x010c65d4(0x10e8d,lVar4,0);
            bRam00000000049ffef6 = 1;
          }
          lVar9 = *(long *)(lVar10 + 0x28);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_LinkedList<object>__AddLast
                    (lVar9,lVar4,_mm_Method_System_Collections_Generic_LinkedList<_iTween>_AddLast__
                    );
          if ((bRam00000000049ffef7 & 1) == 0) {
            func_0x010c65d4(0x10e8c);
            bRam00000000049ffef7 = 1;
          }
          if (lVar4 == 0) {
            func_0x010eec64(0);
          }
          uVar14 = *(undefined4 *)(lVar4 + 0x1c);
          lVar9 = *(long *)(lVar10 + 0x20);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_Dictionary<int,_UserUnitRelation>__TryGetValue
                    (lVar9,uVar14,&stack0xffffffffffffffc8,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_List<_iTween>>_TryGetValue__
                    );
          lVar9 = *(long *)(lVar10 + 0x20);
          lVar10 = func_0x010fdda8(_class_System_Collections_Generic_List<_iTween>_TypeInfo);
          System_Collections_Generic_List<tk2dUILayoutItem>___ctor
                    (lVar10,_mm_Method_System_Collections_Generic_List<_iTween>__ctor__);
          if (lVar9 == 0) {
            func_0x010eec64(0);
          }
          System_Collections_Generic_Dictionary<int,_tk2dFontChar>__set_Item
                    (lVar9,uVar14,lVar10,
                     _mm_Method_System_Collections_Generic_Dictionary<int,_List<_iTween>>_set_Item__
                    );
          if (lVar10 == 0) {
            func_0x010eec64(0);
            lVar10 = 0;
          }
          System_Collections_Generic_List<tk2dUILayoutItem>__Add
                    (lVar10,lVar4,_mm_Method_System_Collections_Generic_List<_iTween>_Add__);
          return;
        }
      }
      return;
    }
    return;
  }
  return;
}


