using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public class SetActiveScene : FsmStateAction
	{
		public enum SceneReferenceOptions
		{
			SceneAtBuildIndex = 0,
			SceneAtIndex = 1,
			SceneByName = 2,
			SceneByPath = 3,
			SceneByGameObject = 4
		}

		public SceneReferenceOptions sceneReference;

		public FsmString sceneByName;

		public FsmInt sceneAtBuildIndex;

		public FsmInt sceneAtIndex;

		public FsmString sceneByPath;

		public FsmOwnerDefault sceneByGameObject;

		public FsmBool success;

		public FsmEvent successEvent;

		public FsmBool sceneFound;

		public FsmEvent sceneNotActivatedEvent;

		public FsmEvent sceneNotFoundEvent;

		private Scene _scene;

		private bool _sceneFound;

		private bool _success;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetActivate()
		{
		}
	}
}
