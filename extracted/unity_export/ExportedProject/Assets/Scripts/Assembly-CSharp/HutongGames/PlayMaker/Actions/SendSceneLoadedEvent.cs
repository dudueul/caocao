using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public class SendSceneLoadedEvent : FsmStateAction
	{
		public FsmEvent sceneLoaded;

		public FsmEvent sceneLoadedSafe;

		public static Scene lastLoadedScene;

		public static LoadSceneMode lastLoadedMode;

		private int _loaded;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void SceneManager_sceneLoaded(Scene scene, LoadSceneMode mode)
		{
		}

		public override void OnUpdate()
		{
		}

		public override void OnExit()
		{
		}

		public override string ErrorCheck()
		{
			return null;
		}
	}
}
