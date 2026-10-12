// name: GuildWar.Backend.PImpl.SessionImpl$$UpdateWebAuthKey
// entry: 01b31a8c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__UpdateWebAuthKey(long *param_1)

{
  long lVar1;
  undefined8 uVar2;
  long *plVar3;
  long lVar4;
  undefined8 uVar5;
  long *plVar6;
  long lVar7;
  float fVar8;
  float fVar9;
  float fVar10;
  undefined8 uStack_58;
  
  if ((bRam00000000049fbe05 & 1) == 0) {
    func_0x010c65d4(0xc032);
    bRam00000000049fbe05 = 1;
  }
  fVar9 = *(float *)((long)param_1 + 0x6c);
  fVar8 = (float)UnityEngine_Time__get_deltaTime(0,0);
  fVar10 = *(float *)(param_1 + 0xe);
  *(float *)((long)param_1 + 0x6c) = fVar9 + fVar8;
  fVar8 = (float)UnityEngine_Time__get_deltaTime(0,0);
  *(float *)(param_1 + 0xe) = fVar10 + fVar8;
  if (((3600.0 <= fVar10 + fVar8) && ((param_1[0xd] & 0xffU) == 0)) &&
     (3600.0 <= (float)((ulong)param_1[0xd] >> 0x20))) {
    *(undefined1 *)(param_1 + 0xd) = 1;
    if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar1 = Singleton<iTweenManager>__get_instance
                      (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
    uVar5 = _mm_Method_GuildWar_Backend_PImpl_SessionImpl_OnWebGetAuthKey__;
    uVar2 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
    HttpRequest_ResponseCallback___ctor(uVar2,param_1,uVar5,0);
    plVar3 = (long *)func_0x01101ad4(_class_object___TypeInfo,2);
    if (plVar3 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((_str_userId != 0) &&
       (lVar4 = func_0x010fdfcc(_str_userId,*(undefined8 *)(*plVar3 + 0x40)), lVar4 == 0)) {
      uVar5 = func_0x010efe90();
      func_0x010eebec(uVar5,0,0);
    }
    lVar4 = _str_userId;
    if ((int)plVar3[3] == 0) {
      uVar5 = func_0x010ef850();
      func_0x010eebec(uVar5,0,0);
    }
    plVar3[4] = lVar4;
    plVar6 = (long *)(**(code **)(*param_1 + 0x170))(param_1,*(undefined8 *)(*param_1 + 0x178));
    if (plVar6 == (long *)0x0) {
      func_0x010eec64(0);
    }
    uStack_58 = (**(code **)(*plVar6 + 0x200))(plVar6,*(undefined8 *)(*plVar6 + 0x208));
    lVar4 = func_0x010fdd08(_class_ulong_TypeInfo,&uStack_58);
    if ((lVar4 != 0) && (lVar7 = func_0x010fdfcc(lVar4,*(undefined8 *)(*plVar3 + 0x40)), lVar7 == 0)
       ) {
      uVar5 = func_0x010efe90();
      func_0x010eebec(uVar5,0,0);
    }
    if (*(uint *)(plVar3 + 3) < 2) {
      uVar5 = func_0x010ef850();
      func_0x010eebec(uVar5,0,0);
    }
    plVar3[5] = lVar4;
    if (lVar1 == 0) {
      func_0x010eec64(0);
    }
    HttpRequest__Send(lVar1,_str__guildWar_getAuthKey,_str_POST,0,uVar2,plVar3,0);
  }
  return;
}


