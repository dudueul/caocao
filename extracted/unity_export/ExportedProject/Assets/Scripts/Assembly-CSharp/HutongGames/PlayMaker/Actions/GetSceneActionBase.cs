using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public abstract class GetSceneActionBase : FsmStateAction
	{
		public enum SceneReferenceOptions
		{
			SceneAtIndex = 0,
			SceneByName = 1,
			SceneByPath = 2
		}

		public enum SceneSimpleReferenceOptions
		{
			SceneAtIndex = 0,
			SceneByName = 1
		}

		public enum SceneBuildReferenceOptions
		{
			SceneAtBuildIndex = 0,
			SceneByName = 1
		}

		public enum SceneAllReferenceOptions
		{
			ActiveScene = 0,
			SceneAtIndex = 1,
			SceneByName = 2,
			SceneByPath = 3,
			SceneByGameObject = 4
		}

		public SceneAllReferenceOptions sceneReference;

		public FsmInt sceneAtIndex;

		public FsmString sceneByName;

		public FsmString sceneByPath;

		public FsmOwnerDefault sceneByGameObject;

		public FsmBool sceneFound;

		public FsmEvent sceneFoundEvent;

		public FsmEvent sceneNotFoundEvent;

		protected Scene _scene;

		protected bool _sceneFound;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
