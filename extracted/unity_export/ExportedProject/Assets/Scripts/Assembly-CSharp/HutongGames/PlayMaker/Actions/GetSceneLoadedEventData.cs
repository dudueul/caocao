using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public class GetSceneLoadedEventData : FsmStateAction
	{
		public FsmEnum loadedMode;

		public FsmString name;

		public FsmString path;

		public FsmBool isValid;

		public FsmInt buildIndex;

		public FsmBool isLoaded;

		public FsmBool isDirty;

		public FsmInt rootCount;

		public FsmArray rootGameObjects;

		private Scene _scene;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoGetSceneProperties()
		{
		}
	}
}
