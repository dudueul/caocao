// name: ServerSetting$$GetAssetbundleBuildBassServerSetting
// entry: 015d1720

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8
ServerSetting__GetAssetbundleBuildBassServerSetting
          (undefined8 param_1,int param_2,undefined8 param_3)

{
  ulong uVar1;
  undefined8 uVar2;
  undefined4 uVar3;
  
  if ((bRam00000000049f8bdc & 1) == 0) {
    func_0x010c65d4(0xbfe3);
    bRam00000000049f8bdc = 1;
  }
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = System_String__op_Equality(0,param_3,_str_stage,0);
  if ((uVar1 & 1) == 0) {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar1 = System_String__op_Equality(0,param_3,_str_dev,0);
    uVar3 = 1;
    if ((uVar1 & 1) == 0) {
      uVar3 = 4;
    }
  }
  else {
    uVar3 = 4;
    if (param_2 < 100) {
      if (param_2 == 1) {
        uVar2 = func_0x010fdda8(_class_StageTWN_TypeInfo);
        StageTWN___ctor(uVar2,0);
        return uVar2;
      }
      if (param_2 == 10) {
        uVar2 = func_0x010fdda8(_class_StageCHN_TypeInfo);
        StageCHN___ctor(uVar2,0);
        return uVar2;
      }
    }
    else {
      if (param_2 == 100) {
        uVar2 = func_0x010fdda8(_class_StageJPN_TypeInfo);
        StageJPN___ctor(uVar2,0);
        return uVar2;
      }
      if (param_2 == 10000) {
        uVar2 = func_0x010fdda8(_class_StageGLB_TypeInfo);
        StageGLB___ctor(uVar2,0);
        return uVar2;
      }
      if (param_2 == 1000) {
        uVar2 = func_0x010fdda8(_class_Stage_TypeInfo);
        Stage___ctor(uVar2,0);
        return uVar2;
      }
    }
  }
  if (((*(byte *)(_class_ServerSetting_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_ServerSetting_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam00000000049f8bdd & 1) == 0) {
    func_0x010c65d4(0xbfe5);
    bRam00000000049f8bdd = 1;
  }
  if (99 < param_2) {
    if (param_2 == 100) {
      switch(uVar3) {
      case 0:
        goto code_r0x015d1568;
      case 1:
        uVar2 = func_0x010fdda8(_class_DevJPN_TypeInfo);
        DevJPN___ctor(uVar2,0);
        return uVar2;
      default:
        return 0;
      case 3:
        uVar2 = func_0x010fdda8(_class_StageJPN_TypeInfo);
        StageJPN___ctor(uVar2,0);
        return uVar2;
      case 4:
        uVar2 = func_0x010fdda8(_class_LiveJPN_TypeInfo);
        LiveJPN___ctor(uVar2,0);
        return uVar2;
      }
    }
    if (param_2 == 10000) {
      switch(uVar3) {
      case 0:
        goto code_r0x015d1568;
      case 1:
        uVar2 = func_0x010fdda8(_class_DevGLB_TypeInfo);
        DevGLB___ctor(uVar2,0);
        return uVar2;
      default:
        return 0;
      case 3:
        uVar2 = func_0x010fdda8(_class_StageGLB_TypeInfo);
        StageGLB___ctor(uVar2,0);
        return uVar2;
      case 4:
        uVar2 = func_0x010fdda8(_class_LiveGLB_TypeInfo);
        LiveGLB___ctor(uVar2,0);
        return uVar2;
      }
    }
    if (param_2 == 1000) {
      switch(uVar3) {
      case 0:
        goto code_r0x015d1568;
      case 1:
        uVar2 = func_0x010fdda8(_class_Dev_TypeInfo);
        Dev___ctor(uVar2,0);
        return uVar2;
      case 2:
        uVar2 = func_0x010fdda8(_class_DevAsLive_TypeInfo);
        DevAsLive___ctor(uVar2,0);
        return uVar2;
      case 3:
        uVar2 = func_0x010fdda8(_class_Stage_TypeInfo);
        Stage___ctor(uVar2,0);
        return uVar2;
      case 4:
        uVar2 = func_0x010fdda8(_class_Live_TypeInfo);
        Live___ctor(uVar2,0);
        return uVar2;
      default:
        return 0;
      }
    }
    return 0;
  }
  if (param_2 == 1) {
    uVar2 = 0;
    switch(uVar3) {
    case 0:
      goto code_r0x015d1568;
    case 1:
      uVar2 = func_0x010fdda8(_class_DevTWN_TypeInfo);
      DevTWN___ctor(uVar2,0);
      break;
    case 3:
      uVar2 = func_0x010fdda8(_class_StageTWN_TypeInfo);
      StageTWN___ctor(uVar2,0);
      break;
    case 4:
      uVar2 = func_0x010fdda8(_class_LiveTWN_TypeInfo);
      LiveTWN___ctor(uVar2,0);
    }
  }
  else {
    if (param_2 != 10) {
      return 0;
    }
    uVar2 = 0;
    switch(uVar3) {
    case 0:
code_r0x015d1568:
      uVar2 = func_0x010fdda8(_class_Local_TypeInfo);
      Local___ctor(uVar2,0);
      break;
    case 1:
      uVar2 = func_0x010fdda8(_class_DevCHN_TypeInfo);
      DevCHN___ctor(uVar2,0);
      break;
    case 3:
      uVar2 = func_0x010fdda8(_class_StageCHN_TypeInfo);
      StageCHN___ctor(uVar2,0);
      break;
    case 4:
      uVar2 = func_0x010fdda8(_class_LiveCHN_TypeInfo);
      LiveCHN___ctor(uVar2,0);
    }
  }
  return uVar2;
}


