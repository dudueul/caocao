## evidence_id

ev_006cf70e693b070cee0f224e554902d2914c8ee5e5305bbba653e5017ae3cab6

## subject.name

caocao.apk

## subject.digest

| Key    | Value                                                            |
|--------|------------------------------------------------------------------|
| sha256 | 3a5bd5bcaecb4cc250695589d98aeff2b63da709fb6e9867afe68467c6c37d06 |

## subject.format

apk

## subject.architecture

null

## subject.local_path

/home/jtk/works/jojo/_release/caocao.apk

## provider

| Key     | Value         |
|---------|---------------|
| id      | jadx          |
| name    | JADX headless |
| version | 0.7.1         |

## analysis_profile

null

## predicate_type

rea.analysis

## operation

inspect_android_class

## parameters

| Key        | Value                                    |
|------------|------------------------------------------|
| path       | /home/jtk/works/jojo/_release/caocao.apk |
| class_name | com.nexon.caocao.iab.CaoCaoIABActivity   |

## raw_result.server

| Key     | Value           |
|---------|-----------------|
| name    | rea-jadx-bridge |
| version | 1               |

## raw_result.bridge_sha256

5a5a4e32264fbe597a329d7ad4b06a6266b7b31243e86b0263dbddcecaf351a1

## raw_result.jar_sha256

6e5eacf500b64292bfb73c49797c1958f6ee44646e43e868039ae7feb573ff75

## raw_result.loaded

| Key            | Value                              |
|----------------|------------------------------------|
| state          | LOADED                             |
| apk_path       | /tmp/rea-android-5NO4GH/target.apk |
| class_count    | 5229                               |
| resource_count | 4863                               |
| threads        | 1                                  |
| resources      | full                               |

## raw_result.calls

| operation         | input           | response        |
|-------------------|-----------------|-----------------|
| rea_jvm_status    | [object Object] | [object Object] |
| load_apk          | [object Object] | [object Object] |
| get_class_summary | [object Object] | [object Object] |

## normalized_result.engine

| Key             | Value                                                            |
|-----------------|------------------------------------------------------------------|
| name            | jadx-headless-mcp                                                |
| version         | 0.7.1                                                            |
| artifact_sha256 | 6e5eacf500b64292bfb73c49797c1958f6ee44646e43e868039ae7feb573ff75 |
| source_revision | 5844800a486d2248fa949b74c9896285f5b54de2                         |
| worker_count    | 1                                                                |
| heap_limit_mib  | 2488                                                             |

## normalized_result.class_name

com.nexon.caocao.iab.CaoCaoIABActivity

## normalized_result.methods

| name                        | reported_signature                                                                                             | overload_index | is_constructor | dex_descriptor |
|-----------------------------|----------------------------------------------------------------------------------------------------------------|----------------|----------------|----------------|
| <clinit>                    | <clinit>(): void                                                                                               | 0              | false          |                |
| <init>                      | <init>(): void                                                                                                 | 0              | true           |                |
| CheckInitIabHelper          | CheckInitIabHelper(): boolean                                                                                  | 0              | false          |                |
| ConsumeProduct              | ConsumeProduct(java.lang.String): void                                                                         | 0              | false          |                |
| ConsumeProduct              | ConsumeProduct(java.lang.String[]): void                                                                       | 1              | false          |                |
| DisposeHelper               | DisposeHelper(): void                                                                                          | 0              | false          |                |
| InitIabHelper               | InitIabHelper(java.lang.String): void                                                                          | 0              | false          |                |
| PurchaseProduct             | PurchaseProduct(java.lang.String, java.lang.String): void                                                      | 0              | false          |                |
| PurchaseSubscription        | PurchaseSubscription(java.lang.String, java.lang.String): void                                                 | 0              | false          |                |
| QueryInventory              | QueryInventory(): void                                                                                         | 0              | false          |                |
| QueryInventory              | QueryInventory(java.lang.String[]): void                                                                       | 1              | false          |                |
| QueryInventory              | QueryInventory(java.lang.String[], java.lang.String[]): void                                                   | 2              | false          |                |
| StartPurchaseFlow           | StartPurchaseFlow(java.lang.String, java.lang.String, java.lang.String): void                                  | 0              | false          |                |
| access$000                  | access$000(com.nexon.caocao.iab.CaoCaoIABActivity): com.android.iab.util.IabHelper                             | 0              | false          |                |
| access$102                  | access$102(com.nexon.caocao.iab.CaoCaoIABActivity, boolean): boolean                                           | 0              | false          |                |
| access$200                  | access$200(com.nexon.caocao.iab.CaoCaoIABActivity, com.android.iab.util.Inventory): java.lang.String           | 0              | false          |                |
| access$300                  | access$300(com.nexon.caocao.iab.CaoCaoIABActivity, java.lang.String, java.lang.String, java.lang.String): void | 0              | false          |                |
| access$400                  | access$400(com.nexon.caocao.iab.CaoCaoIABActivity, com.android.iab.util.Purchase): java.lang.String            | 0              | false          |                |
| enableDebugLogging          | enableDebugLogging(boolean): void                                                                              | 0              | false          |                |
| getHelper                   | getHelper(): com.android.iab.util.IabHelper                                                                    | 0              | false          |                |
| getPurchaseFinishedListener | getPurchaseFinishedListener(): com.android.iab.util.IabHelper$OnIabPurchaseFinishedListener                    | 0              | false          |                |
| instance                    | instance(): com.nexon.caocao.iab.CaoCaoIABActivity                                                             | 0              | false          |                |
| inventoryToJson             | inventoryToJson(com.android.iab.util.Inventory): java.lang.String                                              | 0              | false          |                |
| purchaseToJson              | purchaseToJson(com.android.iab.util.Purchase): java.lang.String                                                | 0              | false          |                |
| skuDetailsToJson            | skuDetailsToJson(com.android.iab.util.SkuDetails): java.lang.String                                            | 0              | false          |                |
| startProxyPurchaseActivity  | startProxyPurchaseActivity(java.lang.String, boolean, java.lang.String): void                                  | 0              | false          |                |
| verifyDeveloperPayload      | verifyDeveloperPayload(com.android.iab.util.Purchase): boolean                                                 | 0              | false          |                |

## normalized_result.fields

| name                              | reported_type                                                 |
|-----------------------------------|---------------------------------------------------------------|
| CALLBACK_BILLING_INIT_FAILED      | java.lang.String                                              |
| CALLBACK_BILLING_INIT_SUCCEED     | java.lang.String                                              |
| CALLBACK_CONSUME_PURCHASE_FAILED  | java.lang.String                                              |
| CALLBACK_CONSUME_PURCHASE_SUCCEED | java.lang.String                                              |
| CALLBACK_PURCHASE_FAILED          | java.lang.String                                              |
| CALLBACK_PURCHASE_SUCCEED         | java.lang.String                                              |
| CALLBACK_QUERY_INVENTORY_FAILED   | java.lang.String                                              |
| CALLBACK_QUERY_INVENTORY_SUCCEED  | java.lang.String                                              |
| CAOCAO_IAB_EVENTMANAGER           | java.lang.String                                              |
| RC_REQUEST                        | int                                                           |
| _instance                         | com.nexon.caocao.iab.CaoCaoIABActivity                        |
| consumeFinishedListener           | com.android.iab.util.IabHelper$OnConsumeFinishedListener      |
| consumeMultiFinishedListener      | com.android.iab.util.IabHelper$OnConsumeMultiFinishedListener |
| debugLogging                      | boolean                                                       |
| iabPurchaseFinishedListener       | com.android.iab.util.IabHelper$OnIabPurchaseFinishedListener  |
| initialized                       | boolean                                                       |
| mHelper                           | com.android.iab.util.IabHelper                                |
| mLogTag                           | java.lang.String                                              |
| queryInventoryFinishedListner     | com.android.iab.util.IabHelper$QueryInventoryFinishedListener |
| sendRequest                       | boolean                                                       |

## normalized_result.inner_classes

com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass1,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass10,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass11,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass12,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass2,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass3,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass4,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass5,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass6,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass7,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass8,com.nexon.caocao.iab.CaoCaoIABActivity.AnonymousClass9

## confidence

observed

## authority

shipped-artifact

## environment

null

## limitations

Static decompiler observations do not establish runtime behavior; the APK is never executed.,Method signatures use provider display types; exact DEX descriptors and instruction offsets are unavailable. Overload indices are local to this engine and artifact.,Class inventories use parsed metadata before code generation, including synthetic members that may be omitted from decompiled source. Source positions remain unknown.,Manifest summary fields are extracted by the upstream engine; decoded XML is retained for verification. APK signatures, split APKs, native libraries and Android runtime capture are not verified by this provider.

## locations



## evidence_links

