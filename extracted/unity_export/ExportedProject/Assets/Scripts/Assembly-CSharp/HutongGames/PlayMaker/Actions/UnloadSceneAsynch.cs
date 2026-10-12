using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class UnloadSceneAsynch : FsmStateAction
	{
		public enum SceneReferenceOptions
		{
			ActiveScene = 0,
			SceneAtBuildIndex = 1,
			SceneAtIndex = 2,
			SceneByName = 3,
			SceneByPath = 4,
			SceneByGameObject = 5
		}

		public SceneReferenceOptions sceneReference;

		public FsmString sceneByName;

		public FsmInt sceneAtBuildIndex;

		public FsmInt sceneAtIndex;

		public FsmString sceneByPath;

		public FsmOwnerDefault sceneByGameObject;

		public FsmInt operationPriority;

		public FsmFloat progress;

		public FsmBool isDone;

		public FsmEvent doneEvent;

		public FsmEvent sceneNotFoundEvent;

		private AsyncOperation _asyncOperation;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private bool DoUnLoadAsynch()
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
