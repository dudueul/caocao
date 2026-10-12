using System.Collections.Generic;
using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class LoadSceneAsynch : FsmStateAction
	{
		public GetSceneActionBase.SceneSimpleReferenceOptions sceneReference;

		public FsmString sceneByName;

		public FsmInt sceneAtIndex;

		public FsmEnum loadSceneMode;

		public FsmBool allowSceneActivation;

		public FsmInt operationPriority;

		public FsmInt aSyncOperationHashCode;

		public FsmFloat progress;

		public FsmBool isDone;

		public FsmBool pendingActivation;

		public FsmEvent doneEvent;

		public FsmEvent pendingActivationEvent;

		public FsmEvent sceneNotFoundEvent;

		private AsyncOperation _asyncOperation;

		private int _asynchOperationUid;

		private bool pendingActivationCallBackDone;

		public static Dictionary<int, AsyncOperation> aSyncOperationLUT;

		private static int aSynchUidCounter;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private bool DoLoadAsynch()
		{
			return false;
		}

		public override void OnUpdate()
		{
		}

		public override void OnExit()
		{
		}
	}
}
