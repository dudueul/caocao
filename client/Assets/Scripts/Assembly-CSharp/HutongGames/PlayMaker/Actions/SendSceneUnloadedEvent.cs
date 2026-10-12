using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public class SendSceneUnloadedEvent : FsmStateAction
	{
		[RequiredField]
		public FsmEvent sceneUnloaded;

		public static Scene lastUnLoadedScene;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void SceneManager_sceneUnloaded(Scene scene)
		{
		}

		public override void OnExit()
		{
		}
	}
}
