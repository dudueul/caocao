using System;

namespace HutongGames.PlayMaker.Actions
{
	[Obsolete]
	public class UnloadScene : FsmStateAction
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

		public FsmBool unloaded;

		public FsmEvent unloadedEvent;

		public FsmEvent failureEvent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
