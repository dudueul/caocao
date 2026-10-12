using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public class SendActiveSceneChangedEvent : FsmStateAction
	{
		[RequiredField]
		public FsmEvent activeSceneChanged;

		public static Scene lastPreviousActiveScene;

		public static Scene lastNewActiveScene;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void SceneManager_activeSceneChanged(Scene previousActiveScene, Scene activeScene)
		{
		}

		public override void OnExit()
		{
		}
	}
}
