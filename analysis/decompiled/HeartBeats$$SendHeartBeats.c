// name: HeartBeats$$SendHeartBeats
// entry: 016cb41c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void HeartBeats__SendHeartBeats(undefined8 param_1)

{
  byte bVar1;
  uint uVar2;
  ulong uVar3;
  long lVar4;
  long lVar5;
  long *plVar6;
  undefined8 *puVar7;
  long lVar8;
  undefined8 uVar9;
  long lVar10;
  undefined8 uVar11;
  long lVar12;
  int *piVar13;
  
  if ((bRam00000000049f97a7 & 1) == 0) {
    func_0x010c65d4(0x6a24);
    bRam00000000049f97a7 = 1;
  }
  uVar3 = MyUserData__get_hasInstance(0,0);
  if ((uVar3 & 1) != 0) {
    lVar4 = MyUserData__get_instance(0,0);
    if (lVar4 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar4 + 0x268) != '\0') {
      if (((*(byte *)(_class_PatcherUtil_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_PatcherUtil_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar4 = PatcherUtil__GetBundleTarget(0,0);
      if (((*(byte *)(_class_Singleton<ResourceManager>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<ResourceManager>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Singleton<ResourceManager>_TypeInfo);
      }
      lVar5 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<ResourceManager>_get_instance__);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      plVar6 = (long *)ResourceManager__get_patchProcessor(lVar5,0);
      if (plVar6 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar5 = *plVar6;
      if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
        uVar3 = 0;
        piVar13 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
        do {
          if (*(long *)(piVar13 + -2) == _class_IPatchProcessor_TypeInfo) {
            puVar7 = (undefined8 *)(lVar5 + (ulong)(*piVar13 + 2) * 0x10 + 0x130);
            goto code_r0x016cb59c;
          }
          uVar3 = uVar3 + 1;
          piVar13 = piVar13 + 4;
        } while (uVar3 < *(ushort *)(lVar5 + 0x122));
      }
      puVar7 = (undefined8 *)func_0x010bc584(plVar6,_class_IPatchProcessor_TypeInfo,2);
code_r0x016cb59c:
      lVar5 = (*(code *)*puVar7)(plVar6,puVar7[1]);
      if (((*(byte *)(_class_Singleton<HttpRequest>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<HttpRequest>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_Singleton<HttpRequest>_TypeInfo);
      }
      lVar8 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<HttpRequest>_get_instance__);
      uVar11 = _mm_Method_HeartBeats_OnHeartBeats__;
      uVar9 = func_0x010fdda8(_class_HttpRequest_ResponseCallback_TypeInfo);
      HttpRequest_ResponseCallback___ctor(uVar9,param_1,uVar11,0);
      plVar6 = (long *)func_0x01101ad4(_class_object___TypeInfo,6);
      if (plVar6 == (long *)0x0) {
        func_0x010eec64(0);
      }
      if ((_str_revision != 0) &&
         (lVar10 = func_0x010fdfcc(_str_revision,*(undefined8 *)(*plVar6 + 0x40)), lVar10 == 0)) {
        uVar11 = func_0x010efe90();
        func_0x010eebec(uVar11,0,0);
      }
      lVar10 = _str_revision;
      if ((int)plVar6[3] == 0) {
        uVar11 = func_0x010ef850();
        func_0x010eebec(uVar11,0,0);
      }
      plVar6[4] = lVar10;
      if (((*(byte *)(_class_CurrentBundleVersion_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_CurrentBundleVersion_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = **(long **)(_class_CurrentBundleVersion_TypeInfo + 0xb8);
      if ((lVar10 != 0) &&
         (lVar12 = func_0x010fdfcc(lVar10,*(undefined8 *)(*plVar6 + 0x40)), lVar12 == 0)) {
        uVar11 = func_0x010efe90();
        func_0x010eebec(uVar11,0,0);
      }
      if (*(uint *)(plVar6 + 3) < 2) {
        uVar11 = func_0x010ef850();
        func_0x010eebec(uVar11,0,0);
      }
      plVar6[5] = lVar10;
      if ((_str_buildTarget != 0) &&
         (lVar10 = func_0x010fdfcc(_str_buildTarget,*(undefined8 *)(*plVar6 + 0x40)), lVar10 == 0))
      {
        uVar11 = func_0x010efe90();
        func_0x010eebec(uVar11,0,0);
      }
      lVar10 = _str_buildTarget;
      if (*(uint *)(plVar6 + 3) < 3) {
        uVar11 = func_0x010ef850();
        func_0x010eebec(uVar11,0,0);
      }
      plVar6[6] = lVar10;
      if ((lVar4 != 0) &&
         (lVar10 = func_0x010fdfcc(lVar4,*(undefined8 *)(*plVar6 + 0x40)), lVar10 == 0)) {
        uVar11 = func_0x010efe90();
        func_0x010eebec(uVar11,0,0);
      }
      if (*(uint *)(plVar6 + 3) < 4) {
        uVar11 = func_0x010ef850();
        func_0x010eebec(uVar11,0,0);
      }
      plVar6[7] = lVar4;
      lVar4 = 0;
      if ((_str_assetBundle != 0) &&
         (lVar4 = func_0x010fdfcc(_str_assetBundle,*(undefined8 *)(*plVar6 + 0x40)), lVar4 == 0)) {
        uVar11 = func_0x010efe90();
        lVar4 = func_0x010eebec(uVar11,0,0);
      }
      lVar10 = _str_assetBundle;
      if (*(uint *)(plVar6 + 3) < 5) {
        uVar11 = func_0x010ef850();
        lVar4 = func_0x010eebec(uVar11,0,0);
      }
      plVar6[8] = lVar10;
      if ((lVar5 != 0) &&
         (lVar4 = func_0x010fdfcc(lVar5,*(undefined8 *)(*plVar6 + 0x40)), lVar4 == 0)) {
        uVar11 = func_0x010efe90();
        lVar4 = func_0x010eebec(uVar11,0,0);
      }
      if (*(uint *)(plVar6 + 3) < 6) {
        uVar11 = func_0x010ef850();
        lVar4 = func_0x010eebec(uVar11,0,0);
      }
      plVar6[9] = lVar5;
      if (lVar8 == 0) {
        lVar4 = func_0x010eec64(0);
      }
      uVar11 = HttpRequest__Construct(lVar4,_str__user_heartBeats,_str_POST,0,uVar9,0,0,plVar6);
      lVar4 = *(long *)(lVar8 + 0x30);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a0137d & 1) == 0) {
        func_0x010c65d4(0x6b97,uVar11,0);
        bRam0000000004a0137d = 1;
      }
      uVar2 = System_Collections_Generic_Queue<string>__Contains
                        (lVar4,uVar11,
                         _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Contains__
                        );
      if ((uVar2 & 1) == 0) {
        System_Collections_Generic_Queue<MessageEventArgs>__Enqueue
                  (lVar4,uVar11,
                   _mm_Method_System_Collections_Generic_Queue<HttpRequest_HttpRequestContext>_Enqueue__
                  );
        bVar1 = HttpRequest_HttpRequestQueue__FindRequestNeed2ShowIndicator(lVar4);
        *(byte *)(lVar4 + 0x28) = bVar1 & 1;
      }
      return;
    }
  }
  return;
}


