using UnityEngine.SceneManagement;

namespace HutongGames.PlayMaker.Actions
{
	public class GetSceneUnloadedEventData : FsmStateAction
	{
		public FsmString name;

		public FsmString path;

		public FsmInt buildIndex;

		public FsmBool isValid;

		public FsmBool isLoaded;

		public FsmBool isDirty;

		public FsmInt rootCount;

		public FsmArray rootGameObjects;

		public bool everyFrame;

		private Scene _scene;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetSceneProperties()
		{
		}
	}
}
