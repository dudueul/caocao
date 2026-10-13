// name: Multiplay.MultiplayManager$$OnStepRequested
// entry: 013ab900

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnStepRequested(long param_1,long param_2)

{
  long lVar1;
  int iVar2;
  undefined4 uVar3;
  uint uVar4;
  long *plVar5;
  long *plVar6;
  long lVar7;
  ulong uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  long lVar11;
  long lVar12;
  code *pcVar13;
  undefined8 uVar14;
  float fVar15;
  
  if ((bRam00000000049f7b1f & 1) == 0) {
    func_0x010c65d4(0x8a68);
    bRam00000000049f7b1f = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar10 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  uVar10 = Util__GetData(0,uVar10,_str_targetUid,0,0);
  if ((bRam00000000049f7afb & 1) == 0) {
    func_0x010c65d4(0x8a85);
    bRam00000000049f7afb = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  plVar5 = (long *)func_0x010fdda8(_class_LitJson_JsonWriter_TypeInfo);
  LitJson_JsonWriter___ctor(plVar5,0);
  if (plVar5 == (long *)0x0) {
    func_0x010eec64(0);
    LitJson_JsonWriter__WriteObjectStart(0,0);
    func_0x010eec64(0);
  }
  else {
    LitJson_JsonWriter__WriteObjectStart(plVar5,0);
  }
  LitJson_JsonWriter__WritePropertyName(plVar5,_str_step,0);
  plVar6 = *(long **)(param_1 + 0x40);
  if (plVar6 == (long *)0x0) {
code_r0x013a6080:
    uVar3 = 8;
code_r0x013a6084:
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
  }
  else {
    iVar2 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
    if (iVar2 != 6) {
code_r0x013a6064:
      plVar6 = *(long **)(param_1 + 0x40);
      if (plVar6 == (long *)0x0) goto code_r0x013a6080;
      uVar3 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
      goto code_r0x013a6084;
    }
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar7 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = *(undefined8 *)(lVar7 + 0x128);
    uVar3 = *(undefined4 *)(lVar7 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar8 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar14,uVar3,0);
    if ((uVar8 & 1) == 0) goto code_r0x013a6064;
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uVar3 = 7;
  }
  LitJson_JsonWriter__Write(plVar5,uVar3,0);
  plVar6 = *(long **)(param_1 + 0x40);
  if (plVar6 == (long *)0x0) {
    uVar4 = 8;
  }
  else {
    uVar4 = (**(code **)(*plVar6 + 0x170))(plVar6,*(undefined8 *)(*plVar6 + 0x178));
    if (8 < uVar4) goto code_r0x013a6404;
  }
  switch(*(undefined4 *)(&UNK_03991a10 + (long)(int)uVar4 * 4)) {
  case 0:
  case 10:
  case 0x10:
    break;
  default:
    return;
  case 0xc:
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WritePropertyName(plVar5,_str_battleKey,0);
    lVar7 = *(long *)(param_1 + 0x48);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = BattlePlay_BattlePlayData__get_battleKey(lVar7,0);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
      LitJson_JsonWriter__Write(0,uVar14,0);
      func_0x010eec64(0);
    }
    else {
      LitJson_JsonWriter__Write(plVar5,uVar14,0);
    }
    LitJson_JsonWriter__WritePropertyName(plVar5,_str_remainingTime,0);
    plVar6 = (long *)&class_PopupCompetitionBan_TypeInfo;
    goto code_r0x013a63dc;
  case 0xd:
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WritePropertyName(plVar5,_str_battleKey,0);
    lVar7 = *(long *)(param_1 + 0x48);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = BattlePlay_BattlePlayData__get_battleKey(lVar7,0);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
      LitJson_JsonWriter__Write(0,uVar14,0);
      func_0x010eec64(0);
    }
    else {
      LitJson_JsonWriter__Write(plVar5,uVar14,0);
    }
    LitJson_JsonWriter__WritePropertyName(plVar5,_str_remainingTime,0);
    plVar6 = (long *)&class_BattleDeck_TypeInfo;
code_r0x013a63dc:
    fVar15 = **(float **)(*plVar6 + 0xb8);
joined_r0x013a6394:
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__Write(plVar5,(int)fVar15,0);
    break;
  case 0xe:
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WritePropertyName(plVar5,_str_battleKey,0);
    lVar7 = *(long *)(param_1 + 0x48);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = BattlePlay_BattlePlayData__get_battleKey(lVar7,0);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
      LitJson_JsonWriter__Write(0,uVar14,0);
      func_0x010eec64(0);
    }
    else {
      LitJson_JsonWriter__Write(plVar5,uVar14,0);
    }
    LitJson_JsonWriter__WritePropertyName(plVar5,_str_remainingTime,0);
    fVar15 = *(float *)(*(long *)(_class_PopupCompetitionOrderSelect_TypeInfo + 0xb8) + 8);
    goto joined_r0x013a6394;
  case 0xf:
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar7 = SceneSingleton<WorldBossBattleController>__get_instance
                      (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = *(undefined8 *)(lVar7 + 0x128);
    uVar3 = *(undefined4 *)(lVar7 + 0x130);
    if (((*(byte *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0x12a) & 1) !=
         0) && (*(int *)(_class_CodeStage_AntiCheat_ObscuredTypes_ObscuredBool_TypeInfo + 0xd8) == 0
               )) {
      func_0x010da55c();
    }
    uVar4 = CodeStage_AntiCheat_ObscuredTypes_ObscuredBool__op_Implicit(0,uVar14,uVar3,0);
    if ((uVar4 & 1) != 0) break;
  case 0xb:
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__WritePropertyName(plVar5,_str_battleKey,0);
    lVar7 = *(long *)(param_1 + 0x48);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar14 = BattlePlay_BattlePlayData__get_battleKey(lVar7,0);
    if (plVar5 == (long *)0x0) {
      func_0x010eec64(0);
    }
    LitJson_JsonWriter__Write(plVar5,uVar14,0);
  }
code_r0x013a6404:
  if (plVar5 == (long *)0x0) {
    func_0x010eec64(0);
    LitJson_JsonWriter__WriteObjectEnd(0,0);
    func_0x010eec64(0);
  }
  else {
    LitJson_JsonWriter__WriteObjectEnd(plVar5,0);
  }
  uVar14 = (**(code **)(*plVar5 + 0x160))(plVar5,*(undefined8 *)(*plVar5 + 0x168));
  uVar9 = func_0x010fdda8(_class_<>__AnonType46<ulong,_string>_TypeInfo);
  <>__AnonType46<ulong,_string>___ctor
            (uVar9,uVar10,uVar14,_mm_Method_<>__AnonType46<ulong,_string>__ctor__);
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar14 = LitJson_JsonMapper__ToJson(0,uVar9,0);
  uVar10 = _str_responseStep;
  uVar8 = MyUserData__get_hasInstance(0,0);
  if ((uVar8 & 1) != 0) {
    lVar7 = MyUserData__get_instance(0,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar7 + 0x268) != '\0') {
      lVar7 = *(long *)(param_1 + 0x30);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar10,uVar14,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = System_String__Format(0,_str____0____1__,uVar10,uVar14,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar14 = LitJson_JsonMapper__ToObject(0,uVar10,0);
      lVar11 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar10 = _str__;
      System_Object___ctor(lVar11,0);
      *(undefined4 *)(lVar11 + 0x14) = 2;
      *(undefined4 *)(lVar11 + 0x18) = 0;
      *(undefined8 *)(lVar11 + 0x30) = uVar14;
      *(undefined8 *)(lVar11 + 0x20) = uVar10;
      *(undefined4 *)(lVar11 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar11 + 0x10) = 4;
      lVar12 = lVar7;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar12 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar7 + 0x48);
      if (*(long *)(lVar7 + 0x50) == 0) {
        lVar12 = func_0x010eec64(0);
      }
      uVar10 = SocketIO_Encoder__Encode(lVar12,lVar11);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar10,0);
      return;
    }
  }
  uVar8 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar8 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar10 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = UnityEngine_Object__op_Inequality(0,uVar10,0,0);
  if ((uVar8 & 1) == 0) {
    return;
  }
  lVar7 = *(long *)(param_1 + 0x30);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Close(lVar7,0);
  uVar10 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam0000000004a04120 & 1) == 0) {
    func_0x010c65d4(0x906e,uVar10,0);
    bRam0000000004a04120 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  pcVar13 = pcRam0000000004a04118;
  if ((pcRam0000000004a04118 == (code *)0x0) &&
     (pcVar13 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar13 == (code *)0x0)) {
    uVar14 = func_0x010f000c(&UNK_039a20c7);
    func_0x010eebec(uVar14,0,0);
  }
  pcRam0000000004a04118 = pcVar13;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04118)(uVar10,0);
  return;
}


