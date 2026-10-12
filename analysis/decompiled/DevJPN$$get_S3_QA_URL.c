// name: DevJPN$$get_S3_QA_URL
// entry: 023a4134

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 DevJPN__get_S3_QA_URL(void)

{
  if ((bRam0000000004a007ff & 1) == 0) {
    func_0x010c65d4(0x37cf);
    bRam0000000004a007ff = 1;
  }
  return _str_https___nxm_caocao_jp_qa_s3_amazonaws_com;
}


